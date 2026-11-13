package com.example.bikingpro

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/** Aktueller Zustand der Aufzeichnung; die MainActivity beobachtet diese Daten live. */
data class TrackingState(
    val isTracking: Boolean = false,
    val speedKmh: Double = 0.0,
    val distanceMeters: Double = 0.0,
    val elapsedSeconds: Long = 0,
    val avgSpeedKmh: Double = 0.0,
    val maxSpeedKmh: Double = 0.0,
    val minSpeedKmh: Double = 0.0,
    val ascentMeters: Double = 0.0,
    val routePoints: List<RoutePoint> = emptyList()
)

/** Vordergrund-Dienst, der die GPS-Position während der Fahrt aufzeichnet. */
class TrackingService : Service() {

    companion object {
        const val ACTION_START = "com.example.bikingpro.action.START"
        const val ACTION_STOP = "com.example.bikingpro.action.STOP"
        private const val CHANNEL_ID = "tracking_channel"
        private const val NOTIFICATION_ID = 1

        private val _state = MutableStateFlow(TrackingState())
        val state: StateFlow<TrackingState> = _state
    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var fusedClient: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null
    private var startTime = 0L
    private var maxSpeedKmh = 0.0
    private var minSpeedKmh = Double.MAX_VALUE

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            val location = result.lastLocation ?: return
            val speedKmh = location.speed * 3.6
            var distance = _state.value.distanceMeters
            var ascent = _state.value.ascentMeters
            val previous = lastLocation
            // Bewegung unter 2 km/h gilt als Pause und wird nicht gewertet.
            if (previous != null && speedKmh > 2.0) {
                distance += previous.distanceTo(location).toDouble()
                val altPrev = previous.altitude
                val altNow = location.altitude
                // Nur positive Höhenunterschiede zählen als Anstieg.
                if (!altNow.isNaN() && altNow > altPrev) {
                    ascent += altNow - altPrev
                }
            }
            lastLocation = location
            if (speedKmh > maxSpeedKmh) maxSpeedKmh = speedKmh
            if (speedKmh > 2.0 && speedKmh < minSpeedKmh) minSpeedKmh = speedKmh
            val elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000
            val avgSpeedKmh = if (elapsedSeconds > 0) distance / elapsedSeconds * 3.6 else 0.0
            val points = _state.value.routePoints +
                    RoutePoint(
                        location.latitude,
                        location.longitude,
                        if (location.hasAltitude()) location.altitude else null
                    )
            _state.value = TrackingState(
                isTracking = true,
                speedKmh = speedKmh,
                distanceMeters = distance,
                elapsedSeconds = elapsedSeconds,
                avgSpeedKmh = avgSpeedKmh,
                maxSpeedKmh = maxSpeedKmh,
                minSpeedKmh = if (minSpeedKmh == Double.MAX_VALUE) 0.0 else minSpeedKmh,
                ascentMeters = ascent,
                routePoints = points
            )
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> stopTracking()
            else -> startTracking()
        }
        return START_NOT_STICKY
    }

    private fun startTracking() {
        createChannel()
        val notification = buildNotification()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }

        startTime = System.currentTimeMillis()
        lastLocation = null
        maxSpeedKmh = 0.0
        minSpeedKmh = Double.MAX_VALUE
        _state.value = TrackingState(isTracking = true)

        fusedClient = LocationServices.getFusedLocationProviderClient(this)
        val hasPermission = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (hasPermission) {
            val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000L).build()
            fusedClient?.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        }
    }

    private fun stopTracking() {
        fusedClient?.removeLocationUpdates(locationCallback)
        fusedClient = null
        val state = _state.value
        if (state.isTracking && state.distanceMeters > 0) {
            serviceScope.launch {
                RideDb.get(applicationContext).insert(
                    Ride(
                        startTime = startTime,
                        endTime = System.currentTimeMillis(),
                        distanceMeters = state.distanceMeters,
                        durationSeconds = state.elapsedSeconds,
                        avgSpeedKmh = state.avgSpeedKmh,
                        maxSpeedKmh = state.maxSpeedKmh,
                        minSpeedKmh = state.minSpeedKmh,
                        ascentMeters = state.ascentMeters,
                        routePoints = state.routePoints
                    )
                )
                finishStop()
            }
        } else {
            finishStop()
        }
    }

    private fun finishStop() {
        _state.value = TrackingState()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    private fun buildNotification(): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_text))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_LOW
            )
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }
}