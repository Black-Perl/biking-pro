package com.example.bikingpro

import android.content.Context

/** Verwaltet die beim ersten Start abgefragte Kontakt-E-Mail-Adresse für
 *  die Nutzung der OpenStreetMap-Karten. Der Eintrag wird dauerhaft in den
 *  SharedPreferences gespeichert und übersteht App-Updates. */
object OsmContact {

    private const val PREFS_NAME = "biking_pro_settings"
    private const val KEY_EMAIL = "osm_contact_email"

    /** Liefert die gespeicherte E-Mail-Adresse (leer, falls keine hinterlegt). */
    fun getEmail(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_EMAIL, "")?.trim() ?: ""
    }

    /** Speichert die E-Mail-Adresse dauerhaft. */
    fun setEmail(context: Context, email: String) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_EMAIL, email.trim())
            .apply()
    }

    /** Prüft, ob bereits eine E-Mail-Adresse hinterlegt ist. */
    fun isSet(context: Context): Boolean = getEmail(context).isNotEmpty()

    /** Baut den User-Agent für OpenStreetMap-Kachelanfragen. */
    fun userAgent(context: Context): String =
        "BikingPro/1.2 (Android; Kontakt: ${getEmail(context)})"
}