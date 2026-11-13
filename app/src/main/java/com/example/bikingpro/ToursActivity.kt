package com.example.bikingpro

import android.app.AlertDialog
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Xml
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.util.Locale

/** Seite mit den geplanten Touren: GPX-Import, Auswahl für die Hauptkarte,
 *  Umbenennen über das Stiftsymbol und Löschen über das Papierkorbsymbol. */
class ToursActivity : AppCompatActivity() {

    companion object {
        /** Aktuell zur Anzeige auf der Hauptkarte ausgewählte Tour. */
        val selectedTour = MutableStateFlow<Tour?>(null)
    }

    private var loadedTours: List<Tour> = emptyList()

    private val gpxImporter =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) importGpx(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tours)

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<Button>(R.id.btnImport).setOnClickListener {
            gpxImporter.launch(
                arrayOf("application/gpx+xml", "application/octet-stream", "*/*")
            )
        }
        findViewById<Button>(R.id.btnClearSelection).setOnClickListener {
            selectedTour.value = null
            Toast.makeText(this, getString(R.string.tour_cleared), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        loadTours()
    }

    private fun loadTours() {
        lifecycleScope.launch {
            loadedTours = withContext(Dispatchers.IO) {
                RideDb.get(applicationContext).getAllTours()
            }
            val listView = findViewById<ListView>(R.id.listTours)
            val emptyView = findViewById<TextView>(R.id.tvEmptyTours)
            if (loadedTours.isEmpty()) {
                listView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            } else {
                listView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
                listView.adapter = ToursAdapter()
            }
        }
    }

    private fun selectTour(tour: Tour) {
        selectedTour.value = tour
        Toast.makeText(this, getString(R.string.tour_selected), Toast.LENGTH_SHORT).show()
    }

    private fun showRenameDialog(tour: Tour) {
        val input = EditText(this)
        input.setText(tour.name)
        input.setSelection(input.text.length)
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_rename_title))
            .setView(input)
            .setPositiveButton(getString(R.string.action_save)) { _, _ ->
                val newName = input.text.toString().trim()
                if (newName.isNotEmpty()) renameTour(tour, newName)
            }
            .setNegativeButton(getString(R.string.action_cancel), null)
            .show()
    }

    private fun renameTour(tour: Tour, newName: String) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                RideDb.get(applicationContext).renameTour(tour.id, newName)
            }
            if (selectedTour.value?.id == tour.id) {
                selectedTour.value = tour.copy(name = newName)
            }
            Toast.makeText(
                applicationContext, getString(R.string.tour_renamed), Toast.LENGTH_SHORT
            ).show()
            loadTours()
        }
    }

    private fun showDeleteDialog(tour: Tour) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_delete_title))
            .setMessage(getString(R.string.delete_tour_confirm))
            .setPositiveButton(getString(R.string.action_delete)) { _, _ -> deleteTour(tour) }
            .setNegativeButton(getString(R.string.action_cancel), null)
            .show()
    }

    private fun deleteTour(tour: Tour) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                RideDb.get(applicationContext).deleteTour(tour.id)
            }
            if (selectedTour.value?.id == tour.id) {
                selectedTour.value = null
            }
            Toast.makeText(
                applicationContext, getString(R.string.tour_deleted), Toast.LENGTH_SHORT
            ).show()
            loadTours()
        }
    }

    private fun tourLengthKm(tour: Tour): Double {
        var meters = 0.0
        var previous: RoutePoint? = null
        for (point in tour.routePoints) {
            val prev = previous
            if (prev != null) {
                val results = FloatArray(1)
                Location.distanceBetween(prev.lat, prev.lon, point.lat, point.lon, results)
                meters += results[0].toDouble()
            }
            previous = point
        }
        return meters / 1000.0
    }

    private fun importGpx(uri: Uri) {
        lifecycleScope.launch {
            val success = withContext(Dispatchers.IO) {
                try {
                    contentResolver.openInputStream(uri)?.use { input ->
                        val points = parseGpx(input)
                        if (points.size < 2) return@use false
                        val name = queryDisplayName(uri)
                            ?: getString(R.string.tour_default_name)
                        RideDb.get(applicationContext).insertTour(
                            Tour(name = name, routePoints = points)
                        )
                        true
                    } ?: false
                } catch (e: Exception) {
                    false
                }
            }
            if (success) {
                Toast.makeText(
                    applicationContext, getString(R.string.tour_imported), Toast.LENGTH_SHORT
                ).show()
                loadTours()
            } else {
                Toast.makeText(
                    applicationContext, getString(R.string.import_failed), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun queryDisplayName(uri: Uri): String? {
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (index >= 0 && cursor.moveToFirst()) return cursor.getString(index)
        }
        return null
    }

    /** Liest Track- und Routenpunkte aus einer GPX-Datei. */
    private fun parseGpx(input: InputStream): List<RoutePoint> {
        val points = mutableListOf<RoutePoint>()
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(input, null)
        var event = parser.eventType
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                val tag = parser.name.lowercase()
                if (tag == "trkpt" || tag == "rtept") {
                    val lat = parser.getAttributeValue(null, "lat")?.toDoubleOrNull()
                    val lon = parser.getAttributeValue(null, "lon")?.toDoubleOrNull()
                    val ele = parser.getAttributeValue(null, "ele")?.toDoubleOrNull()
                    if (lat != null && lon != null) {
                        points.add(RoutePoint(lat, lon, ele))
                    }
                }
            }
            event = parser.next()
        }
        return points
    }

    /** Adapter für die Tourenliste mit Stift- und Papierkorbsymbol je Eintrag. */
    private inner class ToursAdapter : BaseAdapter() {
        override fun getCount() = loadedTours.size
        override fun getItem(position: Int): Tour = loadedTours[position]
        override fun getItemId(position: Int) = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: layoutInflater.inflate(R.layout.item_tour, parent, false)
            val tour = loadedTours[position]
            view.findViewById<TextView>(R.id.tvTourName).text = tour.name
            view.findViewById<TextView>(R.id.tvTourInfo).text = String.format(
                Locale.GERMANY, "%.2f km, %d Punkte",
                tourLengthKm(tour), tour.routePoints.size
            )
            view.setOnClickListener { selectTour(tour) }
            view.findViewById<ImageButton>(R.id.btnEditTour)
                .setOnClickListener { showRenameDialog(tour) }
            view.findViewById<ImageButton>(R.id.btnDeleteTour)
                .setOnClickListener { showDeleteDialog(tour) }
            return view
        }
    }
}