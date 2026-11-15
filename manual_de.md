# Biking Pro - Benutzerhandbuch (Deutsch)

Stand: App-Version 1.2. Dieses Handbuch beschreibt sämtliche Funktionen der App für den täglichen Gebrauch.

## 1. Worum es geht

Biking Pro ist eine Fahrrad-App für Android. Sie zeichnet Fahrten über das GPS des Geräts auf, zeigt Geschwindigkeit, Strecke und Fahrzeit live an, stellt die Route auf einer OpenStreetMap-Karte dar und verwaltet Fahrten sowie importierte GPX-Touren. Es gibt keine Werbung, kein Abo und keine Nutzerkonten; sämtliche Daten bleiben auf Ihrem Gerät. Die Oberfläche ist mehrsprachig (Deutsch, Englisch, Französisch, Spanisch, Dänisch, Niederländisch, Italienisch); standardmäßig folgt sie der Gerätesprache und lässt sich in den Einstellungen auch fest wählen (siehe Abschnitt 8).

**Systemvoraussetzungen**

| Voraussetzung | Beschreibung |
|---|---|
| Betriebssystem | Android 8.0 oder neuer |
| Ortung | GPS beziehungsweise Netzwerkbasierte Ortung im Gerät aktiviert |
| Berechtigungen | Genauer Standort (für Aufzeichnung und Positions-Anzeige); Benachrichtigungen (ab Android 13, für den Hinweis während der Aufzeichnung) |
| Internet | Nur zum Laden der Kartenausschnitte von OpenStreetMap; die Aufzeichnung selbst funktioniert auch offline |

**Benötigte Berechtigungen im Überblick**

| Berechtigung | Wofür | Wann wird gefragt |
|---|---|---|
| Genauer Standort | Aufzeichnung der Fahrt, Anzeige der eigenen Position auf der Karte | Beim ersten Druck auf "Start"; danach dauerhaft gespeichert, sofern Sie nicht widerrufen |
| Benachrichtigungen | Dauerhafter Hinweis "Fahrt wird aufgezeichnet" während der Fahrt | Ab Android 13 beim ersten Druck auf "Start" |

## 2. Erster Start: Kontaktadresse für die Kartennutzung

Beim allerersten Start erscheint ein Dialog mit dem Titel "Kontakt-Adresse für Kartennutzung":

1. Die Karten stammen von OpenStreetMap. Deren Nutzungsbedingungen verlangen eine Kontaktadresse, die bei jedem Kartenaufruf als Kennung übermittelt wird.
2. Tragen Sie Ihre E-Mail-Adresse ein und wählen Sie "Speichern".
3. Ohne gültige Eintragung bleibt die Karte gesperrt; der Dialog lässt sich nicht abbrechen.

Die Adresse wird ausschließlich lokal auf Ihrem Gerät gespeichert, bei App-Updates beibehalten und danach nie wieder abgefragt. Sie wird nicht an Dritte weitergegeben; sie geht nur als Teil der Kennung an den OpenStreetMap-Kartendienst. Nach einer Löschung der App-Daten (siehe Abschnitt 8) wird die Abfrage erneut angezeigt.

## 3. Die Hauptseite im Überblick

Die Hauptseite besteht aus folgenden Bereichen:

| Bereich | Bedeutung |
|---|---|
| Live-Anzeige | Aktuelle Geschwindigkeit (km/h), Streckenlänge (km), Fahrzeit, Durchschnitts- und Höchstgeschwindigkeit |
| Start / Stopp | Startet und beendet die Aufzeichnung einer Fahrt |
| Karte | OpenStreetMap-Karte mit der eigenen Position, einer eingeblendeten geplanten Tour und der laufenden Aufzeichnung |
| Karte folgt der Fahrt | Schalter: Halten Sie ihn aktiv, zieht die Karte während der Fahrt automatisch mit |
| Frühere Fahrten | Öffnet die Liste aller gespeicherten Fahrten |
| Geplante Touren | Öffnet die Tourenverwaltung mit GPX-Import |
| Einstellungen | Öffnet die Einstellungsseite mit Sichern/Wiederherstellen |

Die Karte unterstützt die üblichen Gesten: Ziehen zum Verschieben, Auf- und Zuziehen beziehungsweise Drehen mit zwei Fingern zum Zoomen.

## 4. Eine Fahrt aufzeichnen

So zeichnen Sie eine Fahrt auf:

1. Drücken Sie "Start". Beim allerersten Mal fragt die App die Standort-Berechtigung (und ab Android 13 zusätzlich die Benachrichtigung) an; bestätigen Sie diese.
2. Die Aufzeichnung läuft nun auch im Hintergrund weiter; eine dauerhafte Benachrichtigung mit dem Hinweis "Fahrt wird aufgezeichnet" weist darauf hin.
3. Die Live-Anzeige aktualisiert sich während der Fahrt fortlaufend.
4. Drücken Sie am Ziel "Stopp". Die Fahrt wird automatisch mit allen Werten gespeichert und erscheint anschließend unter "Frühere Fahrten".

**Was die App während der Fahrt erfasst**

- Positionsabruf im Hochgenauigkeits-Modus, etwa alle zwei Sekunden.
- Streckenlänge, aktuelle Geschwindigkeit, Fahrzeit.
- Durchschnitts- und Höchstgeschwindigkeit.
- Minimale Geschwindigkeit: Nur Werte über 2 km/h fließen ein, damit GPS-Rauschen im Stand keine unsinnigen Werte erzeugt.
- Maximaler Anstieg: Die App summiert positive Höhenänderungen; bergab gefahrene Meter werden nicht abgezogen.

**Praxistipps**

- Für brauchbare Werte sollten Sie sich im Freien bewegen; in Gebäuden ist der GPS-Empfang meist zu schwach.
- Lassen Sie den Bildschirm nicht versehentlich sperren, während Sie die Live-Anzeige beobachten; die Aufzeichnung selbst läuft unabhängig davon weiter.
- Bei sehr schwachem Akku können Sie die Aufzeichnung vor dem Ziel vorzeitig beenden; bis dahin erfasste Daten werden gespeichert.

## 5. Die Karte und die eigene Position

- **Eigene Position:** Blauer Punkt mit weißem Rand; der halbtransparente Kreis um den Punkt zeigt die aktuelle GPS-Genauigkeit. Der Punkt ist auch außerhalb laufender Aufzeichnungen sichtbar.
- **Erster GPS-Empfang:** Beim App-Start liegt die Karte zunächst über Berlin (Platzhalter). Sobald der erste GPS-Empfang vorliegt, springt die Karte automatisch einmalig auf Ihre Position; danach bleibt sie frei bedienbar.
- **Geplante Tour:** Eine ausgewählte Tour wird in Neon-Grün auf der Karte dargestellt.
- **Eigene Aufzeichnung:** Die laufende Fahrt wird in Blau eingezeichnet. Abweichungen zwischen Plan und Ist sind so direkt erkennbar.
- **Karte folgt der Fahrt:** Ist der Schalter aktiv, folgt der Kartenausschnitt während der Aufzeichnung Ihrer Position.

## 6. Frühere Fahrten ansehen und verwalten

**Liste:** "Frühere Fahrten" zeigt alle gespeicherten Fahrten, beginnend mit der neuesten. Jeder Eintrag nennt Datum und Uhrzeit des Starts sowie Strecke, Dauer und Durchschnittsgeschwindigkeit. Ist noch keine Fahrt vorhanden, erscheint der Hinweis "Noch keine aufgezeichneten Fahrten."

**Fahrt löschen:** Das Papierkorbsymbol rechts am Eintrag öffnet eine Sicherheitsabfrage; erst nach Bestätigung mit "Löschen" wird die Fahrt endgültig entfernt. Der Vorgang kann nicht rückgängig gemacht werden.

**Detailseite:** Ein Tipp auf einen Listeneintrag öffnet die Detailansicht mit:

- der Karte, auf der die Fahrtroute eingezeichnet und der Ausschnitt automatisch auf die Route eingestellt wird;
- sechs Statistikwerten: Streckenlänge, Fahrdauer, Höchstgeschwindigkeit, minimale Geschwindigkeit, Durchschnittsgeschwindigkeit und maximaler Anstieg.

**GPX-Export:** Der Button "Als GPX exportieren" öffnet den System-Dateiauswahldialog. Wählen Sie einen Speicherort; die Datei erhält automatisch den Namen im Muster `BikingPro_<Datum>_<Uhrzeit>.gpx` und enthält die Route mit allen Punkten. Damit lässt sich die Fahrt in anderen Karten- oder Trainingsprogrammen weiterverwenden.

## 7. Geplante Touren (GPX-Import)

**Importieren:**

1. Öffnen Sie "Geplante Touren" und drücken Sie "GPX-Datei importieren".
2. Wählen Sie im Dateiauswahldialog eine GPX-Datei aus. Es werden sowohl Trackpunkte (trkpt) als auch Routenpunkte (rtept) unterstützt.
3. Die Tour erscheint in der Liste. Als Name wird der Name aus der GPX-Datei übernommen; enthält die Datei keinen Namen, heißt die Tour "Tour ohne Namen".

Jeder Listeneintrag zeigt die Länge der Tour und die Anzahl der Punkte. Ist noch keine Tour vorhanden, erscheint der Hinweis "Noch keine importierten Touren."

**Auf der Karte anzeigen:** Ein Tipp auf die Tour blendet sie in Neon-Grün auf der Hauptkarte ein; eine Meldung bestätigt die Auswahl. Kehren Sie mit "Zurück" auf die Hauptseite zurück, sehen Sie die geplante Route und Ihre eigene Aufzeichnung übereinander, sobald Sie eine Fahrt starten.

**Auswahl aufheben:** Der Button "Auswahl aufheben" entfernt die Tour wieder von der Hauptkarte.

**Umbenennen:** Das Stiftsymbol öffnet einen Dialog mit dem aktuellen Namen; nach "Speichern" ist die Tour unter dem neuen Namen in der Liste zu finden.

**Löschen:** Das Papierkorbsymbol fragt sicherheitshalber nach und löscht die Tour nach Bestätigung. Ist die gelöschte Tour gerade auf der Karte eingeblendet, wird sie dort automatisch entfernt. Die ursprüngliche GPX-Datei auf dem Gerät bleibt davon unberührt.

## 8. Einstellungen

Über den Button "Einstellungen" auf der Hauptseite gelangen Sie zur Einstellungsseite. Sie umfasst drei Bereiche: "Sichern/Wiederherstellen", "Sprache" und "Über".

### Sichern und Wiederherstellen

Die Sicherung umfasst alle aufgezeichneten Fahrten und geplanten Touren als eine einzelne Datei.

**Sicherung erstellen:**

1. Drücken Sie "Sicherung erstellen".
2. Der System-Dateiauswahldialog öffnet sich; wählen Sie einen Speicherort (etwa den Download-Ordner oder eine Cloud).
3. Die Datei erhält automatisch einen Namen im Muster `biking_pro_backup_<Datum>_<Uhrzeit>.db`.
4. Eine Meldung bestätigt den Abschluss.

**Sicherung wiederherstellen:**

1. Drücken Sie "Sicherung wiederherstellen" und wählen Sie eine zuvor exportierte Datei aus.
2. Die App prüft zunächst das Dateiformat. Nur gültige Biking-Pro-Sicherungen werden übernommen; ungültige Dateien werden mit einer Meldung abgewiesen, ohne die vorhandenen Daten zu verändern.
3. Nach erfolgreicher Wiederherstellung stehen alle enthaltenen Fahrten und Touren wieder zur Verfügung.

**Wichtige Hinweise:**

- Die Sicherung enthält die Datenbank mit Fahrten und Touren. Die Kontaktadresse für die Kartennutzung ist nicht Bestandteil der Datei; auf einem neuen Gerät wird sie daher beim ersten Start erneut abgefragt.
- Beim Gerätewechsel installieren Sie die App zunächst auf dem neuen Gerät, stellen die Sicherung über "Einstellungen" wieder her und erfassen danach die Kontaktadresse.
- App-Updates über eine installierte APK erhalten Ihre Daten grundsätzlich; eine Sicherung ist der zusätzliche Schutz gegen Beschädigung, Neuinstallation oder Gerätewechsel.
- Zusätzlich legt die App vor internen Datenbankumstellungen automatisch eine interne Sicherheitskopie an. Dieser Vorgang ist für Sie nicht sichtbar.

### Sprache

Der Bereich "Sprache" zeigt die aktuell aktive Sprache der App an. Standardmäßig folgt die App der Gerätesprache (unterstützt werden Deutsch, Englisch, Französisch, Spanisch, Dänisch, Niederländisch und Italienisch; bei allen anderen Gerätesprachen greift Englisch).

Über den Button "Sprache wählen" können Sie abweichend davon eine der sieben unterstützten Sprachen fest auswählen:

1. Drücken Sie "Sprache wählen"; eine Auswahl mit dem Systemstandard und allen unterstützten Sprachen erscheint.
2. Wählen Sie eine Sprache; sie wird sofort für die gesamte App angewendet, ohne Neustart.
3. Die Wahl wird dauerhaft gespeichert und überlebt App-Neustarts und Gerätestarts.
4. Der Eintrag "Systemstandard" stellt das ursprüngliche Verhalten wieder her: Die App folgt wieder der Gerätesprache.

Hinweis: Ab Android 13 erscheint die gewählte Sprache zusätzlich in den Systemeinstellungen unter "Apps", "Biking Pro", "Sprachen" und kann auch dort geändert werden. Zahlenformate, Datumsangaben und Uhrzeiten passen sich stets der aktiven Sprache an.

### Über

Der Button "Über" öffnet einen Dialog mit der Versionsnummer der installierten App und einem Verweis auf dieses Benutzerhandbuch im Projektarchiv auf GitHub. Der Button "Benutzerhandbuch öffnen" im Dialog ruft die Seite im Browser auf.

## 9. Datenschutz und Datenhaltung

- Alle Fahrten, Touren und die Kontaktadresse werden ausschließlich lokal in einer Datenbank beziehungsweise in den App-Einstellungen Ihres Geräts gespeichert.
- Es gibt keine Server-Anbindung der App, keine Werbung und kein Abo.
- Die Kartenausschnitte werden vom OpenStreetMap-Dienst geladen; dabei wird die bei der Ersteinrichtung hinterlegte Kontaktadresse als Kennung übermittelt. Andere persönliche Daten werden nicht übertragen.
- Löschen Sie alle Daten, indem Sie in den Android-Einstellungen unter "Apps", "Biking Pro" die App-Daten löschen; die Abfrage der Kontaktadresse erscheint danach erneut.

## 10. Häufige Fragen und Problemlösungen

| Beobachtung | Ursache | Abhilfe |
|---|---|---|
| Blauer Punkt der eigenen Position fehlt | Standort-Berechtigung nicht erteilt oder kein GPS-Empfang | Berechtigung erteilen (einmalig "Start" drücken oder Android-Einstellungen, Apps, Biking Pro, Berechtigungen); im Freien auf GPS-Empfang warten |
| Karte startet über Berlin und springt nicht auf meine Position | Noch kein GPS-Fix | Im Freien auf den ersten GPS-Empfang warten; der Sprung erfolgt automatisch. Berlin ist nur ein Platzhalter |
| Aufzeichnung startet nicht | Standort-Dienst des Geräts deaktiviert | Ortung in den Android-Schnelleinstellungen aktivieren |
| Karte zeigt "Access blocked" | Unzureichende Kennung gegenüber OpenStreetMap | Gültige Kontaktadresse hinterlegen; falls eine falsche Adresse gespeichert wurde, App-Daten löschen und Adresse neu erfassen |
| Keine Benachrichtigung während der Fahrt (ab Android 13) | Benachrichtigungs-Berechtigung verweigert | Berechtigung in den Android-Einstellungen nachträglich erteilen; die Aufzeichnung läuft auch ohne Benachrichtigung |
| GPX-Import meldet "GPX-Datei konnte nicht gelesen werden" | Datei enthält keine Track- oder Routenpunkte oder ist beschädigt | Andere GPX-Datei verwenden; die Datei muss Trackpunkte (trkpt) oder Routenpunkte (rtept) enthalten |
| Wiederherstellen meldet ungültige Datei | Gewählte Datei ist keine Biking-Pro-Sicherung | Nur Dateien verwenden, die über "Einstellungen", "Sicherung erstellen" erzeugt wurden |
| Gewählte Sprache wird nach dem Neustart der App nicht gespeichert (Android 12 und älter) | Die Speicherung der Sprachwahl läuft intern über einen App-Mechanismus | Wahl erneut vornehmen; die aktuelle App-Version speichert die Wahl dauerhaft. Falls das Problem bleibt, App aktualisieren |
| App soll wieder der Gerätesprache folgen | Es wurde eine feste Sprache gewählt | In den Einstellungen unter "Sprache wählen" den Eintrag "Systemstandard" auswählen |
| Geschwindigkeit zeigt unrealistische Werte | GPS-Rauschen, etwa zwischen Häusern | Für die Auswertung Durchschnittswerte heranziehen; Mindestgeschwindigkeit wird von der App ohnehin nur oberhalb von 2 km/h berücksichtigt |

## 11. Bezug und Version

Die App wird als Installationsdatei (APK) über das Projektarchiv unter github.com/Black-Perl/biking-pro bereitgestellt. Vor der Installation muss in Android die Installation aus unbekannten Quellen für Ihre Browser- oder Datei-App erlaubt sein. Neue Versionen werden einfach über die bestehende Installation installiert; Fahrten, Touren und die Kontaktadresse bleiben dabei erhalten. Die installierte Versionsnummer sehen Sie jederzeit im Dialog "Über" in den Einstellungen der App; dieser Dialog verweist ebenfalls auf dieses Benutzerhandbuch im Projektarchiv.

Bei Fragen oder Problemen wenden Sie sich bitte an die in der Veröffentlichung genannte Kontaktadresse des Projekts.---
