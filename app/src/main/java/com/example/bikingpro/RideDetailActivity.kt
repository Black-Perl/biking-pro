package com.example.bikingpro

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Detailseite einer Fahrt: Karte, Statistiken und GPX-Export. */
class RideDetailActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private var currentRide: Ride? = null

    private val germanLocale = Locale.GERMANY

    private val gpxSaver =
        registerForActivityResult(
            ActivityResultContracts.CreateDocument("application/gpx+xml")
        ) { uri ->
            if (uri != null) writeGpx(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_detail)

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }

        Configuration.getInstance().userAgentValue = OsmContact.userAgent(this)
        mapView = findViewById(R.id.mapViewDetail)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)

        findViewById<Button>(R.id.btnExport).setOnClickListener {
            val ride = currentRide ?: return@setOnClickListener
            val name = SimpleDateFormat("yyyyMMdd_HHmm", Locale.GERMANY)
                .format(Date(ride.startTime))
            gpxSaver.launch("BikingPro_$name.gpx")
        }

        loadRide()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    private fun loadRide() {
        val rideId = intent.getLongExtra("rideId", -1L)
        lifecycleScope.launch {
            val ride = withContext(Dispatchers.IO) {
                RideDb.get(applicationContext).getRideById(rideId)
            }
            if (ride == null) {
                finish()
                return@launch
            }
            currentRide = ride
            showRide(ride)
        }
    }

    private fun showRide(ride: Ride) {
        findViewById<TextView>(R.id.tvStatDistance).text = String.format(
            germanLocale, "Streckenlänge: %.2f km", ride.distanceMeters / 1000.0
        )
        findViewById<TextView>(R.id.tvStatDuration).text = String.format(
            germanLocale, "Fahrdauer: %s", formatDuration(ride.durationSeconds)
        )
        findViewById<TextView>(R.id.tvStatMax).text = String.format(
            germanLocale, "Höchstgeschwindigkeit: %.1f km/h", ride.maxSpeedKmh
        )
        findViewById<TextView>(R.id.tvStatMin).text = String.format(
            germanLocale, "Minimale Geschwindigkeit: %.1f km/h", ride.minSpeedKmh
        )
        findViewById<TextView>(R.id.tvStatAvg).text = String.format(
            germanLocale, "Durchschnittsgeschwindigkeit: %.1f km/h", ride.avgSpeedKmh
        )
        findViewById<TextView>(R.id.tvStatAscent).text = String.format(
            germanLocale, "Maximaler Anstieg: %.0f m", ride.ascentMeters
        )

        val geoPoints = ride.routePoints.map { GeoPoint(it.lat, it.lon) }
        if (geoPoints.size >= 2) {
            val polyline = Polyline()
            polyline.setPoints(geoPoints)
            mapView.overlays.add(polyline)
            mapView.zoomToBoundingBox(BoundingBox.fromGeoPoints(geoPoints), false, 64)
        } else if (geoPoints.isNotEmpty()) {
            mapView.controller.setCenter(geoPoints.first())
            mapView.controller.setZoom(16.0)
        }
        mapView.invalidate()
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

    private fun writeGpx(uri: Uri) {
        val ride = currentRide ?: return
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                contentResolver.openOutputStream(uri)?.use { out ->
                    out.write(buildGpx(ride).toByteArray(Charsets.UTF_8))
                }
            }
            Toast.makeText(
                applicationContext, getString(R.string.export_done), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun buildGpx(ride: Ride): String {
        val sb = StringBuilder()
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
        sb.append("<gpx version=\"1.1\" creator=\"Biking Pro\" ")
        sb.append("xmlns=\"http://www.topografix.com/GPX/1/1\">\n")
        sb.append("  <trk>\n")
        sb.append("    <name>Biking Pro Fahrt</name>\n")
        sb.append("    <trkseg>\n")
        for (p in ride.routePoints) {
            sb.append(String.format(Locale.US, "      <trkpt lat=\"%.7f\" lon=\"%.7f\"", p.lat, p.lon))
            if (p.elevation != null) {
                sb.append(String.format(Locale.US, " ele=\"%.2f\"", p.elevation))
            }
            sb.append("/>\n")
        }
        sb.append("    </trkseg>\n")
        sb.append("  </trk>\n")
        sb.append("</gpx>\n")
        return sb.toString()
    }
}