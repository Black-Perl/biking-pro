package com.example.bikingpro

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

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
    private lateinit var btnSettings: Button
    private lateinit var switchFollow: SwitchCompat
    private lateinit var mapView: MapView
    private lateinit var myLocationOverlay: MyLocationNewOverlay

    private var plannedTour: Tour? = null

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grants ->
            if (grants[android.Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                if (::myLocationOverlay.isInitialized) myLocationOverlay.enableMyLocation()
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
        btnSettings = findViewById(R.id.btnSettings)
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
        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        observeTrackingState()
        observePlannedTour()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        if (::myLocationOverlay.isInitialized && hasLocationPermission()) {
            myLocationOverlay.enableMyLocation()
        }
    }

    override fun onPause() {
        if (::myLocationOverlay.isInitialized) {
            myLocationOverlay.disableMyLocation()
        }
        mapView.onPause()
        super.onPause()
    }

    /** Aktiviert die Kartendaten von OpenStreetMap mit der gespeicherten
     *  Kontakt-E-Mail-Adresse, legt das Symbol für die eigene Position als
     *  blauen Punkt mit weißem Rand an und zentriert die Karte einmalig auf
     *  die eigene Position, sobald der erste GPS-Empfang vorliegt. */
    private fun activateMap() {
        Configuration.getInstance().userAgentValue = OsmContact.userAgent(this)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.visibility = View.VISIBLE

        // Eigenen Standort als blauen Punkt anzeigen. Der halbtransparente
        // Kreis um den Punkt zeigt die aktuelle GPS-Genauigkeit an.
        myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), mapView).apply {
            setPersonIcon(createPositionDotBitmap())
            setPersonAnchor(0.5f, 0.5f)
        }
        mapView.overlays.add(myLocationOverlay)
        if (hasLocationPermission()) {
            myLocationOverlay.enableMyLocation()
        }

        // Sobald die erste GPS-Position vorliegt, springt die Karte einmalig
        // dorthin. Danach bleibt sie frei bedienbar; das Nachführen während
        // einer Fahrt übernimmt weiterhin der Schalter "Karte folgt der Fahrt".
        myLocationOverlay.runOnFirstFix {
            runOnUiThread {
                mapView.controller.animateTo(myLocationOverlay.myLocation)
            }
        }
    }

    /** Erzeugt das runde Positionssymbol: weißer Rand für Sichtbarkeit
     *  auf jedem Kartenhintergrund, blauer Kern in der Farbe der
     *  Aufzeichnungslinie. */
    private fun createPositionDotBitmap(): Bitmap {
        val density = resources.displayMetrics.density
        val size = (26 * density).toInt()
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.WHITE
        canvas.drawCircle(size / 2f, size / 2f, size / 2f - 1, paint)
        paint.color = Color.parseColor("#2196F3")
        canvas.drawCircle(size / 2f, size / 2f, size / 2f - 1 - 3 * density, paint)
        return bitmap
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
                    tvSpeed.text = getString(R.string.fmt_speed, state.speedKmh)
                    tvDistance.text =
                        getString(R.string.fmt_distance, state.distanceMeters / 1000.0)
                    tvDuration.text = formatDuration(state.elapsedSeconds)
                    tvAvg.text = getString(R.string.fmt_speed_avg, state.avgSpeedKmh)
                    tvMax.text = getString(R.string.fmt_speed_max, state.maxSpeedKmh)
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
            getString(R.string.fmt_duration_hours, hours, minutes, secs)
        } else {
            getString(R.string.fmt_duration_minutes, minutes, secs)
        }
    }

    /** Zeichnet die geplante Tour (Neon-Grün), die Aufzeichnung (blau)
     *  und den eigenen Standort als blauen Punkt. */
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

        // Das Standort-Symbol muss nach dem Neuaufbau der Overlays erneut
        // hinzugefügt werden, damit der blaue Punkt erhalten bleibt; es
        // wird zuletzt eingefügt und liegt damit über den Linien.
        if (::myLocationOverlay.isInitialized) {
            mapView.overlays.add(myLocationOverlay)
        }

        if (state.isTracking && recordPoints.isNotEmpty() && switchFollow.isChecked) {
            mapView.controller.setCenter(
                GeoPoint(recordPoints.last().lat, recordPoints.last().lon)
            )
        }
        mapView.invalidate()
    }
}