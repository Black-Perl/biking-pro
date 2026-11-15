# Biking Pro - User Manual (English)

As of: app version 1.2. This manual describes all functions of the app for everyday use.

## 1. What it is about

Biking Pro is a cycling app for Android. It records rides using the device's GPS, shows speed, distance and ride time live, displays the route on an OpenStreetMap map, and manages rides as well as imported GPX tours. There is no advertising, no subscription and no user accounts; all data remains on your device. The interface is multilingual (German, English, French, Spanish, Danish, Dutch, Italian); by default it follows the device language and can also be set permanently in the settings (see section 8).

**System requirements**

| Requirement | Description |
|---|---|
| Operating system | Android 8.0 or newer |
| Location | GPS or network-based location enabled on the device |
| Permissions | Precise location (for recording and position display); notifications (from Android 13, for the notice during recording) |
| Internet | Only for loading map tiles from OpenStreetMap; the recording itself also works offline |

**Required permissions at a glance**

| Permission | Purpose | When it is requested |
|---|---|---|
| Precise location | Recording the ride, showing your own position on the map | On the first press of "Start"; afterwards stored permanently unless you revoke it |
| Notifications | Permanent notice "Ride is being recorded …" during the ride | From Android 13 on the first press of "Start" |

## 2. First start: contact address for map usage

On the very first start, a dialog titled "Contact address for map usage" appears:

1. The maps come from OpenStreetMap. Their terms of use require a contact address that is transmitted as an identifier with every map request.
2. Enter your email address and choose "Save".
3. Without a valid entry the map remains locked; the dialog cannot be cancelled.

The address is stored exclusively locally on your device, is kept across app updates and is never requested again. It is not passed on to third parties; it only goes to the OpenStreetMap map service as part of the identifier. After deleting the app data (see section 8), the prompt appears again.

## 3. The main page at a glance

The main page consists of the following areas:

| Area | Meaning |
|---|---|
| Live display | Current speed (km/h), distance (km), ride time, average and maximum speed |
| Start / Stop | Starts and ends the recording of a ride |
| Map | OpenStreetMap map with your own position, a displayed planned tour and the current recording |
| Map follows the ride | Switch: keep it active and the map moves along automatically during the ride |
| Past rides | Opens the list of all saved rides |
| Planned tours | Opens the tour management with GPX import |
| Settings | Opens the settings page with back up/restore |

The map supports the usual gestures: drag to move, pinch apart or together with two fingers to zoom.

## 4. Recording a ride

How to record a ride:

1. Press "Start". The very first time, the app asks for the location permission (and from Android 13 also the notification permission); confirm these.
2. The recording now continues in the background as well; a permanent notification with the notice "Ride is being recorded …" indicates this.
3. The live display updates continuously during the ride.
4. At your destination, press "Stop". The ride is saved automatically with all values and afterwards appears under "Past rides".

**What the app records during a ride**

- Position requests in high-accuracy mode, roughly every two seconds.
- Distance, current speed, ride time.
- Average and maximum speed.
- Minimum speed: only values above 2 km/h are included, so GPS noise while stationary does not produce nonsensical values.
- Maximum ascent: the app sums positive altitude changes; metres ridden downhill are not subtracted.

**Practical tips**

- For usable values, move outdoors; indoors the GPS reception is usually too weak.
- Do not accidentally let the screen lock while you watch the live display; the recording itself continues regardless.
- If the battery is very low, you can end the recording before your destination; the data captured up to that point is saved.

## 5. The map and your own position

- **Own position:** blue dot with a white border; the semi-transparent circle around the dot shows the current GPS accuracy. The dot is also visible outside active recordings.
- **First GPS reception:** when the app starts, the map initially lies over Berlin (placeholder). As soon as the first GPS reception is available, the map automatically jumps to your position once; afterwards it remains freely movable.
- **Planned tour:** a selected tour is shown in neon green on the map.
- **Own recording:** the current ride is drawn in blue. Deviations between plan and actual route are directly visible.
- **Map follows the ride:** when the switch is active, the map section follows your position during the recording.

## 6. Viewing and managing past rides

**List:** "Past rides" shows all saved rides, starting with the newest. Each entry lists the date and start time as well as distance, duration and average speed. If no ride exists yet, the notice "No recorded rides yet." appears.

**Delete a ride:** the bin symbol on the right of an entry opens a confirmation prompt; only after confirming with "Delete" is the ride permanently removed. The action cannot be undone.

**Detail page:** tapping a list entry opens the detail view with:

- the map on which the route is drawn and the section is automatically fitted to the route;
- six statistics: distance, ride time, maximum speed, minimum speed, average speed and maximum ascent.

**GPX export:** the button "Export as GPX" opens the system file picker. Choose a storage location; the file automatically receives a name matching the pattern `BikingPro_<date>_<time>.gpx` and contains the route with all points. This lets you reuse the ride in other map or training programs.

## 7. Planned tours (GPX import)

**Importing:**

1. Open "Planned tours" and press "Import GPX file".
2. Choose a GPX file in the file picker. Both track points (trkpt) and route points (rtept) are supported.
3. The tour appears in the list. The name from the GPX file is adopted; if the file contains no name, the tour is called "Tour without a name".

Each list entry shows the length of the tour and the number of points. If no tour exists yet, the notice "No imported tours yet." appears.

**Show on the map:** tapping a tour displays it in neon green on the main map; a message confirms the selection. Return to the main page with "Back" and you will see the planned route and your own recording on top of each other as soon as you start a ride.

**Clear selection:** the button "Clear selection" removes the tour from the main map again.

**Rename:** the pencil symbol opens a dialog with the current name; after "Save", the tour appears under its new name in the list.

**Delete:** the bin symbol asks for confirmation and deletes the tour after confirmation. If the deleted tour is currently displayed on the map, it is automatically removed there. The original GPX file on the device remains unaffected.

## 8. Settings

Via the "Settings" button on the main page you reach the settings page. It comprises three areas: "Back up/Restore", "Language" and "About".

### Back up and restore

The backup contains all recorded rides and planned tours as a single file.

**Create backup:**

1. Press "Create backup".
2. The system file picker opens; choose a storage location (for example the Downloads folder or a cloud).
3. The file automatically receives a name matching the pattern `biking_pro_backup_<date>_<time>.db`.
4. A message confirms completion.

**Restore backup:**

1. Press "Restore backup" and choose a previously exported file.
2. The app first checks the file format. Only valid Biking Pro backups are accepted; invalid files are rejected with a message, without altering the existing data.
3. After a successful restore, all contained rides and tours are available again.

**Important notes:**

- The backup contains the database with rides and tours. The contact address for map usage is not part of the file; on a new device it is therefore requested again on first start.
- When switching devices, first install the app on the new device, restore the backup via "Settings" and then enter the contact address.
- App updates over an installed APK keep your data in principle; a backup is the additional protection against damage, reinstallation or device change.
- In addition, the app automatically creates an internal backup copy before internal database migrations. This process is not visible to you.

### Language

The "Language" area shows the currently active app language. By default, the app follows the device language (German, English, French, Spanish, Danish, Dutch and Italian are supported; for all other device languages English applies).

Via the "Choose language" button you can select one of the seven supported languages instead:

1. Press "Choose language"; a selection with the system default and all supported languages appears.
2. Choose a language; it is applied immediately to the entire app, without a restart.
3. The choice is stored permanently and survives app and device restarts.
4. The "System default" entry restores the original behaviour: the app follows the device language again.

Note: from Android 13, the chosen language also appears in the system settings under "Apps", "Biking Pro", "Languages" and can also be changed there. Number formats, dates and times always follow the active language.

### About

The "About" button opens a dialog with the version number of the installed app and a reference to this user manual in the project repository on GitHub. The "Open manual" button in the dialog opens the page in the browser.

## 9. Privacy and data storage

- All rides, tours and the contact address are stored exclusively locally in a database or in the app settings on your device.
- The app has no server connection, no advertising and no subscription.
- The map tiles are loaded from the OpenStreetMap service; the contact address stored during initial setup is transmitted as an identifier. No other personal data is transmitted.
- Delete all data by deleting the app data under "Apps", "Biking Pro" in the Android settings; the prompt for the contact address appears again afterwards.

## 10. Frequently asked questions and solutions

| Observation | Cause | Remedy |
|---|---|---|
| Blue dot of own position missing | Location permission not granted or no GPS reception | Grant the permission (press "Start" once or via Android settings, Apps, Biking Pro, Permissions); wait for GPS reception outdoors |
| Map starts over Berlin and does not jump to my position | No GPS fix yet | Outdoors, wait for the first GPS reception; the jump happens automatically. Berlin is only a placeholder |
| Recording does not start | Device location service switched off | Enable location in the Android quick settings |
| Map shows "Access blocked" | Insufficient identifier towards OpenStreetMap | Store a valid contact address; if a wrong address was saved, delete the app data and re-enter the address |
| No notification during the ride (from Android 13) | Notification permission denied | Grant the permission later in the Android settings; the recording also runs without the notification |
| GPX import reports "GPX file could not be read." | File contains no track or route points, or is damaged | Use another GPX file; the file must contain track points (trkpt) or route points (rtept) |
| Restore reports an invalid file | Selected file is not a Biking Pro backup | Only use files created via "Settings", "Create backup" |
| Chosen language is not saved after restarting the app (Android 12 and older) | Saving the language choice runs internally via an app mechanism | Make the choice again; the current app version saves the choice permanently. If the problem persists, update the app |
| App should follow the device language again | A fixed language was selected | In the settings, under "Choose language", select the "System default" entry |
| Speed shows unrealistic values | GPS noise, for example between buildings | Use average values for evaluation; the app only takes the minimum speed into account above 2 km/h anyway |

## 11. Availability and version

The app is provided as an installation file (APK) via the project repository at github.com/Black-Perl/biking-pro. Before installation, Android must allow installing from unknown sources for your browser or file app. New versions are simply installed over the existing installation; rides, tours and the contact address are preserved. You can see the installed version number at any time in the "About" dialog in the app settings; this dialog also refers to this user manual in the project repository.

For questions or problems, please contact the project contact address named in the publication.
