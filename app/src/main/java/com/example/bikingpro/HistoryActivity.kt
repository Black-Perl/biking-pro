package com.example.bikingpro

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Seite mit der Liste aller vergangenen Fahrten; jede Fahrt kann über das
 *  Papierkorbsymbol gelöscht werden. */
class HistoryActivity : AppCompatActivity() {

    private val germanLocale = Locale.GERMANY
    private var loadedRides: List<Ride> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        loadRides()
    }

    private fun loadRides() {
        lifecycleScope.launch {
            loadedRides = withContext(Dispatchers.IO) {
                RideDb.get(applicationContext).getAll()
            }
            val listView = findViewById<ListView>(R.id.listRides)
            val emptyView = findViewById<TextView>(R.id.tvEmptyRides)
            if (loadedRides.isEmpty()) {
                listView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            } else {
                listView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
                listView.adapter = RidesAdapter()
            }
        }
    }

    private fun openRide(ride: Ride) {
        val intent = Intent(this, RideDetailActivity::class.java)
        intent.putExtra("rideId", ride.id)
        startActivity(intent)
    }

    private fun showDeleteDialog(ride: Ride) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_delete_title))
            .setMessage(getString(R.string.delete_ride_confirm))
            .setPositiveButton(getString(R.string.action_delete)) { _, _ -> deleteRide(ride) }
            .setNegativeButton(getString(R.string.action_cancel), null)
            .show()
    }

    private fun deleteRide(ride: Ride) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                RideDb.get(applicationContext).deleteRide(ride.id)
            }
            Toast.makeText(
                applicationContext, getString(R.string.ride_deleted), Toast.LENGTH_SHORT
            ).show()
            loadRides()
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

    /** Adapter für die Fahrtenliste mit Papierkorbsymbol je Eintrag. */
    private inner class RidesAdapter : BaseAdapter() {
        override fun getCount() = loadedRides.size
        override fun getItem(position: Int): Ride = loadedRides[position]
        override fun getItemId(position: Int) = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: layoutInflater.inflate(R.layout.item_ride, parent, false)
            val ride = loadedRides[position]
            val dateFormat = SimpleDateFormat("EEEE, dd.MM.yyyy, HH:mm", germanLocale)
            view.findViewById<TextView>(R.id.tvRideDate).text =
                dateFormat.format(Date(ride.startTime))
            view.findViewById<TextView>(R.id.tvRideInfo).text = String.format(
                germanLocale,
                "%.2f km in %s, Ø %.1f km/h",
                ride.distanceMeters / 1000.0,
                formatDuration(ride.durationSeconds),
                ride.avgSpeedKmh
            )
            view.setOnClickListener { openRide(ride) }
            view.findViewById<ImageButton>(R.id.btnDeleteRide)
                .setOnClickListener { showDeleteDialog(ride) }
            return view
        }
    }
}