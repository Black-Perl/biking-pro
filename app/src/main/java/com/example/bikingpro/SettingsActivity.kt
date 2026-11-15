package com.example.bikingpro

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Einstellungsseite mit den Bereichen "Sichern/Wiederherstellen",
 *  "Sprache" und "Über". Die Sicherung umfasst die komplette
 *  Datenbankdatei mit allen Fahrten und geplanten Touren. */
class SettingsActivity : AppCompatActivity() {

    private val dbFile by lazy { getDatabasePath(RideDb.DATABASE_NAME) }

    private lateinit var languageCurrentView: TextView

    private val exportLauncher =
        registerForActivityResult(
            ActivityResultContracts.CreateDocument("application/octet-stream")
        ) { uri ->
            if (uri != null) exportBackup(uri)
        }

    private val importLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) importBackup(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<Button>(R.id.btnBackupExport).setOnClickListener { startExport() }
        findViewById<Button>(R.id.btnBackupRestore).setOnClickListener { startImport() }
        languageCurrentView = findViewById(R.id.tvLanguageCurrent)
        findViewById<Button>(R.id.btnLanguageSelect).setOnClickListener { showLanguageDialog() }
        findViewById<Button>(R.id.btnAbout).setOnClickListener { showAboutDialog() }
        updateLanguageDisplay()
    }

    private fun startExport() {
        val timestamp =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.GERMANY).format(Date())
        exportLauncher.launch("biking_pro_backup_$timestamp.db")
    }

    private fun startImport() {
        importLauncher.launch(arrayOf("application/octet-stream", "*/*"))
    }

    /** Schreibt die Datenbankdatei an den gewählten Ort. */
    private fun exportBackup(uri: Uri) {
        lifecycleScope.launch {
            val ok = withContext(Dispatchers.IO) {
                try {
                    // Datenbank öffnen (legt die Datei bei allererster Nutzung
                    // an, falls noch keine existiert) und sauber schließen.
                    val db = RideDb.get(applicationContext)
                    db.readableDatabase
                    db.close()
                    if (!dbFile.exists()) return@withContext false
                    contentResolver.openOutputStream(uri)?.use { output ->
                        dbFile.inputStream().use { input ->
                            input.copyTo(output)
                        }
                    } != null
                } catch (e: Exception) {
                    false
                }
            }
            Toast.makeText(
                applicationContext,
                getString(if (ok) R.string.backup_export_done else R.string.backup_failed),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /** Spielt eine zuvor exportierte Sicherung wieder ein. */
    private fun importBackup(uri: Uri) {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                importBackupInternal(uri)
            }
            val message = when (result) {
                ImportResult.SUCCESS -> getString(R.string.backup_import_done)
                ImportResult.INVALID_FILE -> getString(R.string.restore_invalid_file)
                else -> getString(R.string.restore_failed)
            }
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    private enum class ImportResult { SUCCESS, INVALID_FILE, ERROR }

    private fun importBackupInternal(uri: Uri): ImportResult {
        val tempFile = File(cacheDir, "biking_pro_restore.db")
        return try {
            // Gewählte Datei zunächst in eine temporäre Datei kopieren ...
            contentResolver.openInputStream(uri)?.use { input ->
                tempFile.outputStream().use { output -> input.copyTo(output) }
            } ?: return ImportResult.ERROR

            // ... und prüfen, ob es sich um eine SQLite-Datenbank handelt.
            val header = ByteArray(16)
            tempFile.inputStream().use { input ->
                if (input.read(header) < 16 ||
                    !String(header).startsWith("SQLite format 3")
                ) {
                    tempFile.delete()
                    return ImportResult.INVALID_FILE
                }
            }

            // Datenbank schließen, alte Datei entfernen, Sicherung einsetzen.
            val db = RideDb.get(applicationContext)
            db.close()
            dbFile.delete()
            File(dbFile.path + "-journal").delete()
            tempFile.copyTo(dbFile, overwrite = true)
            tempFile.delete()
            ImportResult.SUCCESS
        } catch (e: Exception) {
            ImportResult.ERROR
        } finally {
            tempFile.delete()
        }
    }

    /** Zeigt die aktuell eingestellte App-Sprache an. */
    private fun updateLanguageDisplay() {
        val locales = AppCompatDelegate.getApplicationLocales()
        val active = locales.get(0)
        val current =
            if (locales.isEmpty || active == null)
                getString(R.string.settings_lang_system)
            else active.getDisplayName(active)
        languageCurrentView.text =
            getString(R.string.settings_lang_current, current)
    }

    /** Öffnet die Sprachauswahl: Systemstandard oder eine der unterstützten
     *  Sprachen. Die Wahl wird sofort angewendet; die Oberfläche passt sich
     *  unmittelbar an, und der Eintrag im Manifest (Datei 2) speichert die
     *  Wahl auf Geräten mit Android 8 bis 12 dauerhaft. */
    private fun showLanguageDialog() {
        val labels = mutableListOf(getString(R.string.settings_lang_system))
        LANGUAGES.forEach { tag ->
            val locale = Locale.forLanguageTag(tag)
            labels.add(locale.getDisplayName(locale))
        }
        val locales = AppCompatDelegate.getApplicationLocales()
        val active = locales.get(0)
        val checked =
            if (locales.isEmpty || active == null) 0
            else LANGUAGES.indexOf(active.language) + 1
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.settings_language_section))
            .setSingleChoiceItems(labels.toTypedArray(), checked) { dialog, which ->
                if (which == 0) {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.getEmptyLocaleList()
                    )
                } else {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(LANGUAGES[which - 1])
                    )
                }
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.action_cancel), null)
            .show()
    }

    /** Öffnet den Dialog "Über" mit Versionsnummer und Verweis auf das
     *  Benutzerhandbuch im Projektarchiv auf GitHub. */
    private fun showAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.settings_about_title))
            .setMessage(
                getString(R.string.settings_about_message, appVersion(), MANUAL_URL)
            )
            .setPositiveButton(getString(R.string.settings_about_open_manual)) { _, _ ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(MANUAL_URL)))
            }
            .setNegativeButton(getString(R.string.dialog_close), null)
            .show()
    }

    /** Liest die Versionsnummer der installierten App aus. */
    private fun appVersion(): String = try {
        packageManager.getPackageInfo(packageName, 0).versionName ?: ""
    } catch (e: PackageManager.NameNotFoundException) {
        ""
    }

    companion object {
        /** Sprach-Tags der unterstützten Sprachen. */
        private val LANGUAGES =
            listOf("de", "en", "fr", "es", "da", "nl", "it")

        /** Adresse des Benutzerhandbuchs im Projektarchiv auf GitHub.
         *  Wenn Sie das Handbuch an anderer Stelle im Projektarchiv
         *  ablegen, passen Sie diese Konstante entsprechend an. */
        const val MANUAL_URL = "https://github.com/Black-Perl/biking-pro"
    }
}