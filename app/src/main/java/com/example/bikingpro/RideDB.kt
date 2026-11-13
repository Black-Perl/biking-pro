package com.example.bikingpro

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Locale

/** Ein einzelner Routenpunkt mit optionaler Höhe in Metern. */
data class RoutePoint(val lat: Double, val lon: Double, val elevation: Double? = null)

/** Ein Datensatz für eine abgeschlossene Fahrt. */
data class Ride(
    val id: Long = 0,
    val startTime: Long,
    val endTime: Long,
    val distanceMeters: Double,
    val durationSeconds: Long,
    val avgSpeedKmh: Double,
    val maxSpeedKmh: Double,
    val minSpeedKmh: Double,
    val ascentMeters: Double,
    val routePoints: List<RoutePoint> = emptyList()
)

/** Eine geplante Tour (GPX-Import). */
data class Tour(
    val id: Long = 0,
    val name: String,
    val routePoints: List<RoutePoint> = emptyList()
)

/** Lokale Datenbank (SQLite) für gespeicherte Fahrten und geplante Touren.
 *  Version 4: fügt die Tabelle für geplante Touren hinzu; bestehende
 *  Fahrten der Version 3 bleiben erhalten. */
class RideDb private constructor(context: Context) :
    SQLiteOpenHelper(context, "biking_pro.db", null, 4) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_RIDES)
        db.execSQL(CREATE_TOURS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3) {
            db.execSQL("DROP TABLE IF EXISTS rides")
            db.execSQL(CREATE_RIDES)
        }
        if (oldVersion < 4) {
            db.execSQL(CREATE_TOURS)
        }
    }

    fun insert(ride: Ride) {
        val values = ContentValues().apply {
            put("startTime", ride.startTime)
            put("endTime", ride.endTime)
            put("distanceMeters", ride.distanceMeters)
            put("durationSeconds", ride.durationSeconds)
            put("avgSpeedKmh", ride.avgSpeedKmh)
            put("maxSpeedKmh", ride.maxSpeedKmh)
            put("minSpeedKmh", ride.minSpeedKmh)
            put("ascentMeters", ride.ascentMeters)
            put("routePoints", serializeRoute(ride.routePoints))
        }
        writableDatabase.insert("rides", null, values)
    }

    fun getAll(): List<Ride> {
        val rides = mutableListOf<Ride>()
        readableDatabase.rawQuery(
            "SELECT id, startTime, endTime, distanceMeters, durationSeconds, avgSpeedKmh, " +
                    "maxSpeedKmh, minSpeedKmh, ascentMeters, routePoints " +
                    "FROM rides ORDER BY startTime DESC",
            null
        ).use { cursor ->
            while (cursor.moveToNext()) rides.add(rowToRide(cursor))
        }
        return rides
    }

    fun getRideById(id: Long): Ride? {
        readableDatabase.rawQuery(
            "SELECT id, startTime, endTime, distanceMeters, durationSeconds, avgSpeedKmh, " +
                    "maxSpeedKmh, minSpeedKmh, ascentMeters, routePoints " +
                    "FROM rides WHERE id = ?",
            arrayOf(id.toString())
        ).use { cursor ->
            if (cursor.moveToFirst()) return rowToRide(cursor)
        }
        return null
    }

    private fun rowToRide(cursor: Cursor): Ride = Ride(
        id = cursor.getLong(0),
        startTime = cursor.getLong(1),
        endTime = cursor.getLong(2),
        distanceMeters = cursor.getDouble(3),
        durationSeconds = cursor.getLong(4),
        avgSpeedKmh = cursor.getDouble(5),
        maxSpeedKmh = cursor.getDouble(6),
        minSpeedKmh = cursor.getDouble(7),
        ascentMeters = cursor.getDouble(8),
        routePoints = deserializeRoute(cursor.getString(9))
    )

    fun insertTour(tour: Tour) {
        val values = ContentValues().apply {
            put("name", tour.name)
            put("routePoints", serializeRoute(tour.routePoints))
        }
        writableDatabase.insert("tours", null, values)
    }

    fun getAllTours(): List<Tour> {
        val tours = mutableListOf<Tour>()
        readableDatabase.rawQuery(
            "SELECT id, name, routePoints FROM tours ORDER BY id DESC",
            null
        ).use { cursor ->
            while (cursor.moveToNext()) {
                tours.add(
                    Tour(
                        id = cursor.getLong(0),
                        name = cursor.getString(1),
                        routePoints = deserializeRoute(cursor.getString(2))
                    )
                )
            }
        }
        return tours
    }

    /** Benennt eine geplante Tour um. */
    fun renameTour(id: Long, name: String) {
        val values = ContentValues().apply { put("name", name) }
        writableDatabase.update("tours", values, "id = ?", arrayOf(id.toString()))
    }

    /** Löscht eine geplante Tour. */
    fun deleteTour(id: Long) {
        writableDatabase.delete("tours", "id = ?", arrayOf(id.toString()))
    }

    /** Löscht eine aufgezeichnete Fahrt. */
    fun deleteRide(id: Long) {
        writableDatabase.delete("rides", "id = ?", arrayOf(id.toString()))
    }

    companion object {
        private val CREATE_RIDES = """
            CREATE TABLE rides (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                startTime INTEGER NOT NULL,
                endTime INTEGER NOT NULL,
                distanceMeters REAL NOT NULL,
                durationSeconds INTEGER NOT NULL,
                avgSpeedKmh REAL NOT NULL,
                maxSpeedKmh REAL NOT NULL,
                minSpeedKmh REAL NOT NULL DEFAULT 0,
                ascentMeters REAL NOT NULL DEFAULT 0,
                routePoints TEXT NOT NULL DEFAULT ''
            )
        """.trimIndent()

        private val CREATE_TOURS = """
            CREATE TABLE tours (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                routePoints TEXT NOT NULL DEFAULT ''
            )
        """.trimIndent()

        private fun serializeRoute(points: List<RoutePoint>): String =
            points.joinToString("|") { p ->
                val ele =
                    if (p.elevation != null) String.format(Locale.US, "%.2f", p.elevation) else "-"
                "${p.lat};${p.lon};$ele"
            }

        private fun deserializeRoute(text: String?): List<RoutePoint> {
            if (text.isNullOrBlank()) return emptyList()
            return text.split("|").mapNotNull { entry ->
                val parts = entry.split(";")
                val lat = parts.getOrNull(0)?.toDoubleOrNull() ?: return@mapNotNull null
                val lon = parts.getOrNull(1)?.toDoubleOrNull() ?: return@mapNotNull null
                val ele = parts.getOrNull(2)?.takeIf { it != "-" }?.toDoubleOrNull()
                RoutePoint(lat, lon, ele)
            }
        }

        @Volatile
        private var INSTANCE: RideDb? = null

        fun get(context: Context): RideDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RideDb(context.applicationContext).also { INSTANCE = it }
            }
    }
}