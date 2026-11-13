package com.example.bikingpro

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var tvSpeed: TextView
    private lateinit var tvDistance: TextView
    private lateinit var tvDuration: TextView
    private lateinit var tvAvg: TextView
    private lateinit var tvMax: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnHistory: Button
    private lateinit var btnTours: Button
    private lateinit var switchFollow: SwitchCompat
    private lateinit var mapView: MapView

    private var plannedTour: Tour? = null

    private val germanLocale = Locale.GERMANY

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grants ->
            if (grants[android.Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                startTracking()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSpeed = findViewById(R.id.tvSpeed)
        tvDistance = findViewById(R.id.tvDistance)
        tvDuration = findViewById(R.id.tvDuration)
        tvAvg = findViewById(R.id.tvAvg)
        tvMax = findViewById(R.id.tvMax)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnHistory = findViewById(R.id.btnHistory)
        btnTours = findViewById(R.id.btnTours)
        switchFollow = findViewById(R.id.switchFollow)

        // OpenStreetMap-Karte initialisieren. Die Kartendaten werden erst
        // geladen, wenn die Kontakt-E-Mail-Adresse feststeht.
        mapView = findViewById(R.id.mapView)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(15.5)
        mapView.controller.setCenter(GeoPoint(52.52, 13.4049))

        if (OsmContact.isSet(this)) {
            activateMap()
        } else {
            mapView.visibility = View.GONE
            showEmailDialog()
        }

        btnStart.setOnClickListener {
            if (hasLocationPermission()) startTracking() else requestPermissions()
        }
        btnStop.setOnClickListener { stopTracking() }
        btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        btnTours.setOnClickListener {
            startActivity(Intent(this, ToursActivity::class.java))
        }

        observeTrackingState()
        observePlannedTour()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    /** Aktiviert die Kartendaten von OpenStreetMap mit der gespeicherten
     *  Kontakt-E-Mail-Adresse. */
    private fun activateMap() {
        Configuration.getInstance().userAgentValue = OsmContact.userAgent(this)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.visibility = View.VISIBLE
    }

    /** Fragt die Kontakt-E-Mail-Adresse beim allerersten Start ab.
     *  Der Dialog lässt sich nicht abbrechen; ohne gültige Eingabe
     *  bleibt die Karte gesperrt. Die Eingabe wird dauerhaft gespeichert
     *  und bleibt bei App-Updates erhalten. */
    private fun showEmailDialog() {
        val input = EditText(this)
        input.hint = getString(R.string.email_hint)
        val dialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.email_dialog_title))
            .setMessage(getString(R.string.email_dialog_message))
            .setView(input)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.action_save), null)
            .create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val email = input.text.toString().trim()
                if (email.length < 5 || !email.contains('@') || email.contains(' ')) {
                    input.error = getString(R.string.email_invalid)
                    return@setOnClickListener
                }
                OsmContact.setEmail(this, email)
                dialog.dismiss()
                activateMap()
            }
        }
        dialog.show()
    }

    private fun hasLocationPermission(): Boolean =
        ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        val permissions = mutableListOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(android.Manifest.permission.POST_NOTIFICATIONS)
        }
        permissionLauncher.launch(permissions.toTypedArray())
    }

    private fun startTracking() {
        val intent = Intent(this, TrackingService::class.java)
            .setAction(TrackingService.ACTION_START)
        ContextCompat.startForegroundService(this, intent)
    }

    private fun stopTracking() {
        val intent = Intent(this, TrackingService::class.java)
            .setAction(TrackingService.ACTION_STOP)
        startService(intent)
    }

    private fun observeTrackingState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                TrackingService.state.collect { state ->
                    btnStart.isEnabled = !state.isTracking
                    btnStop.isEnabled = state.isTracking
                    tvSpeed.text = String.format(germanLocale, "%.1f km/h", state.speedKmh)
                    tvDistance.text =
                        String.format(germanLocale, "%.2f km", state.distanceMeters / 1000.0)
                    tvDuration.text = formatDuration(state.elapsedSeconds)
                    tvAvg.text =
                        String.format(germanLocale, "Durchschnitt: %.1f km/h", state.avgSpeedKmh)
                    tvMax.text =
                        String.format(germanLocale, "Maximum: %.1f km/h", state.maxSpeedKmh)
                    updateMap()
                }
            }
        }
    }

    /** Beobachtet die auf der Touren-Seite ausgewählte Tour. */
    private fun observePlannedTour() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                ToursActivity.selectedTour.collect { tour ->
                    plannedTour = tour
                    updateMap()
                }
            }
        }
    }

    private fun formatDuration(seconds: Long): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        return if (hours > 0) {
            String.format(germanLocale, "%d:%02d:%02d h", hours, minutes, secs)
        } else {
            String.format(germanLocale, "%d:%02d min", minutes, secs)
        }
    }

    /** Zeichnet die geplante Tour (hellgrün) und die Aufzeichnung (blau). */
    private fun updateMap() {
        val state = TrackingService.state.value
        mapView.overlays.clear()

        val tourPoints = plannedTour?.routePoints.orEmpty()
        if (tourPoints.size >= 2) {
            val tourLine = Polyline()
            tourLine.outlinePaint.color = Color.parseColor("#39FF14")
            tourLine.outlinePaint.strokeWidth = 8f
            tourLine.setPoints(tourPoints.map { GeoPoint(it.lat, it.lon) })
            mapView.overlays.add(tourLine)
        }

        val recordPoints = state.routePoints
        if (recordPoints.size >= 2) {
            val recordLine = Polyline()
            recordLine.outlinePaint.color = Color.parseColor("#2196F3")
            recordLine.outlinePaint.strokeWidth = 10f
            recordLine.setPoints(recordPoints.map { GeoPoint(it.lat, it.lon) })
            mapView.overlays.add(recordLine)
        }

        if (state.isTracking && recordPoints.isNotEmpty() && switchFollow.isChecked) {
            mapView.controller.setCenter(
                GeoPoint(recordPoints.last().lat, recordPoints.last().lon)
            )
        }
        mapView.invalidate()
    }
}