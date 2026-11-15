# Biking Pro - Gebruikershandleiding (Nederlands)

Stand: app-versie 1.2. Deze handleiding beschrijft alle functies van de app voor dagelijks gebruik.

## 1. Waar het om gaat

Biking Pro is een fiets-app voor Android. Ze legt ritten vast via de GPS van uw apparaat, toont snelheid, afstand en rijtijd live, geeft de route weer op een OpenStreetMap-kaart en beheert ritten en geïmporteerde GPX-routes. Er is geen reclame, geen abonnement en geen gebruikersaccounts; alle gegevens blijven op uw apparaat. De interface is meertalig (Duits, Engels, Frans, Spaans, Deens, Nederlands, Italiaans); standaard volgt ze de apparaattaal en kan zij ook vast worden ingesteld in de instellingen (zie sectie 8).

**Systeemvereisten**

| Vereiste | Beschrijving |
|---|---|
| Besturingssysteem | Android 8.0 of nieuwer |
| Locatiebepaling | GPS of netwerkgebaseerde locatiebepaling ingeschakeld op het apparaat |
| Machtigingen | Nauwkeurige locatie (voor opname en positieweergave); meldingen (vanaf Android 13, voor de melding tijdens de opname) |
| Internet | Alleen voor het laden van de kaarttegels van OpenStreetMap; de opname zelf werkt ook offline |

**Vereiste machtigingen in één oogopslag**

| Machtiging | Waarvoor | Wanneer gevraagd |
|---|---|---|
| Nauwkeurige locatie | Opnemen van de rit, eigen positie op de kaart tonen | Bij de eerste druk op "Start"; daarna permanent opgeslagen, tenzij u haar intrekt |
| Meldingen | Permanente melding "Rit wordt opgenomen …" tijdens de rit | Vanaf Android 13 bij de eerste druk op "Start" |

## 2. Eerste start: contactadres voor kaartgebruik

Bij de allereerste start verschijnt een dialoogvenster met de titel "Contactadres voor kaartgebruik":

1. De kaarten komen van OpenStreetMap. De gebruiksvoorwaarden vereisen een contactadres dat bij elke kaartopvraag als identificatie wordt meegestuurd.
2. Voer uw e-mailadres in en kies "Opslaan".
3. Zonder geldige invoer blijft de kaart vergrendeld; het dialoogvenster kan niet worden geannuleerd.

Het adres wordt uitsluitend lokaal op uw apparaat opgeslagen, blijft behouden bij app-updates en wordt daarna nooit meer gevraagd. Het wordt niet aan derden doorgegeven; het gaat alleen als onderdeel van de identificatie naar de OpenStreetMap-kaartdienst. Na het wissen van de appgegevens (zie sectie 8) verschijnt de vraag opnieuw.

## 3. De hoofdpagina in één oogopslag

De hoofdpagina bestaat uit de volgende onderdelen:

| Onderdeel | Betekenis |
|---|---|
| Liveweergave | Actuele snelheid (km/u), afstand (km), rijtijd, gemiddelde en maximale snelheid |
| Start / Stop | Start en beëindigt de opname van een rit |
| Kaart | OpenStreetMap-kaart met uw eigen positie, een weergegeven geplande route en de lopende opname |
| Kaart volgt de rit | Schakelaar: houd hem actief en de kaart beweegt automatisch mee tijdens de rit |
| Eerdere ritten | Opent de lijst met alle opgeslagen ritten |
| Geplande routes | Opent het routebeheer met GPX-import |
| Instellingen | Opent de instellingenpagina met back-up/herstel |

De kaart ondersteunt de gebruikelijke gebaren: slepen om te verschuiven, met twee vingers knijpen of spreiden om te zoomen.

## 4. Een rit opnemen

Zo neemt u een rit op:

1. Druk op "Start". De allereerste keer vraagt de app de locatiemachtiging aan (en vanaf Android 13 ook de meldingsmachtiging); bevestig deze.
2. De opname loopt nu ook op de achtergrond door; een permanente melding met de tekst "Rit wordt opgenomen …" wijst daarop.
3. De liveweergave vernieuwt zich tijdens de rit doorlopend.
4. Druk bij uw bestemming op "Stop". De rit wordt automatisch met alle waarden opgeslagen en verschijnt daarna onder "Eerdere ritten".

**Wat de app tijdens de rit registreert**

- Locatieopvraging in de modus hoge nauwkeurigheid, ongeveer elke twee seconden.
- Afstand, actuele snelheid, rijtijd.
- Gemiddelde en maximale snelheid.
- Minimale snelheid: alleen waarden boven 2 km/u tellen mee, zodat GPS-ruis bij stilstand geen onzinnige waarden oplevert.
- Maximale stijging: de app telt positieve hoogteverschillen bij; gedaalde meters worden niet afgetrokken.

**Praktische tips**

- Voor bruikbare waarden dient u zich buitenshuis te bevinden; binnenshuis is de GPS-ontvangst meestal te zwak.
- Laat het scherm niet per ongeluk vergrendelen terwijl u de liveweergave volgt; de opname zelf loopt daarvan onafhankelijk door.
- Bij een zeer zwakke accu kunt u de opname vóór de bestemming beëindigen; de tot dan toe vastgelegde gegevens worden opgeslagen.

## 5. De kaart en uw eigen positie

- **Eigen positie:** blauwe stip met witte rand; de halftransparante cirkel rond de stip toont de actuele GPS-nauwkeurigheid. De stip is ook buiten lopende opnames zichtbaar.
- **Eerste GPS-ontvangst:** bij het starten van de app ligt de kaart eerst op Berlijn (plaatshouder). Zodra de eerste GPS-ontvangst plaatsvindt, springt de kaart automatisch eenmalig naar uw positie; daarna blijft zij vrij te bedienen.
- **Geplande route:** een geselecteerde route wordt in neongroen op de kaart weergegeven.
- **Eigen opname:** de lopende rit wordt in blauw getekend. Afwijkingen tussen plan en werkelijkheid zijn zo direct zichtbaar.
- **Kaart volgt de rit:** is de schakelaar actief, dan volgt de kaartuitsnede uw positie tijdens de opname.

## 6. Eerdere ritten bekijken en beheren

**Lijst:** "Eerdere ritten" toont alle opgeslagen ritten, beginnend met de nieuwste. Elk item vermeldt de datum en starttijd alsmede afstand, duur en gemiddelde snelheid. Bestaat er nog geen rit, dan verschijnt de melding "Nog geen opgenomen ritten."

**Rit verwijderen:** het prullenbaksymbool rechts van het item opent een bevestigingsvraag; pas na bevestiging met "Verwijderen" wordt de rit definitief verwijderd. De handeling kan niet ongedaan worden gemaakt.

**Detailpagina:** een tik op een item opent de detailweergave met:

- de kaart, waarop de route is getekend en de kaartuitsnede automatisch op de route wordt afgestemd;
- zes statistische waarden: afstand, duur, maximale snelheid, minimale snelheid, gemiddelde snelheid en maximale stijging.

**GPX-export:** de knop "Exporteren als GPX" opent de bestandskeuzedialoog van het systeem. Kies een opslaglocatie; het bestand krijgt automatisch een naam volgens het patroon `BikingPro_<datum>_<tijd>.gpx` en bevat de route met alle punten. Zo kunt u de rit in andere kaart- of trainingsprogramma's hergebruiken.

## 7. Geplande routes (GPX-import)

**Importeren:**

1. Open "Geplande routes" en druk op "GPX-bestand importeren".
2. Kies in de bestandskeuzedialoog een GPX-bestand. Zowel trackpunten (trkpt) als routepunten (rtept) worden ondersteund.
3. De route verschijnt in de lijst. De naam uit het GPX-bestand wordt overgenomen; bevat het bestand geen naam, dan heet de route "Route zonder naam".

Elk item in de lijst toont de lengte van de route en het aantal punten. Bestaat er nog geen route, dan verschijnt de melding "Nog geen geïmporteerde routes."

**Op de kaart tonen:** een tik op de route toont haar in neongroen op de hoofdkaart; een melding bevestigt de selectie. Keer met "Terug" terug naar de hoofdpagina en u ziet de geplande route en uw eigen opname over elkaar zodra u een rit start.

**Selectie opheffen:** de knop "Selectie opheffen" verwijdert de route weer van de hoofdkaart.

**Naam wijzigen:** het potloodsymbool opent een dialoogvenster met de huidige naam; na "Opslaan" staat de route onder de nieuwe naam in de lijst.

**Verwijderen:** het prullenbaksymbool vraagt ter bevestiging en verwijdert de route na bevestiging. Is de verwijderde route op dat moment op de kaart getoond, dan wordt zij daar automatisch verwijderd. Het oorspronkelijke GPX-bestand op het apparaat blijft ongewijzigd.

## 8. Instellingen

Via de knop "Instellingen" op de hoofdpagina komt u op de instellingenpagina. Zij omvat drie onderdelen: "Back-up maken/Herstellen", "Taal" en "Over".

### Back-up maken en herstellen

De back-up bevat alle opgenomen ritten en geplande routes als één bestand.

**Back-up maken:**

1. Druk op "Back-up maken".
2. De bestandskeuzedialoog van het systeem opent; kies een opslaglocatie (bijvoorbeeld de downloadmap of een cloud).
3. Het bestand krijgt automatisch een naam volgens het patroon `biking_pro_backup_<datum>_<tijd>.db`.
4. Een melding bevestigt de voltooiing.

**Back-up herstellen:**

1. Druk op "Back-up herstellen" en kies een eerder geëxporteerd bestand.
2. De app controleert eerst de bestandsindeling. Alleen geldige Biking Pro-back-ups worden overgenomen; ongeldige bestanden worden met een melding afgewezen, zonder de bestaande gegevens te wijzigen.
3. Na een geslaagd herstel zijn alle vervatte ritten en routes weer beschikbaar.

**Belangrijke aanwijzingen:**

- De back-up bevat de database met ritten en routes. Het contactadres voor kaartgebruik maakt geen deel uit van het bestand; op een nieuw apparaat wordt het daarom bij de eerste start opnieuw gevraagd.
- Bij een apparaatwisseling installeert u eerst de app op het nieuwe apparaat, herstelt u de back-up via "Instellingen" en voert u daarna het contactadres in.
- App-updates over een geïnstalleerde APK behouden uw gegevens in beginsel; een back-up is de extra bescherming tegen beschadiging, herinstallatie of apparaatwisseling.
- Daarnaast maakt de app vóór interne databasewijzigingen automatisch een interne reservekopie. Dit verloop is voor u niet zichtbaar.

### Taal

Het onderdeel "Taal" toont de momenteel actieve app-taal. Standaard volgt de app de apparaattaal (Duits, Engels, Frans, Spaans, Deens, Nederlands en Italiaans worden ondersteund; bij alle andere apparaattalen geldt Engels).

Met de knop "Taal kiezen" kunt u daarvan afwijkend een van de zeven ondersteunde talen vast kiezen:

1. Druk op "Taal kiezen"; er verschijnt een keuze met de systeemtaal en alle ondersteunde talen.
2. Kies een taal; zij wordt onmiddellijk op de hele app toegepast, zonder herstart.
3. De keuze wordt permanent opgeslagen en overleeft app- en apparaatherstarts.
4. Het item "Systeemtaal" herstelt het oorspronkelijke gedrag: de app volgt weer de apparaattaal.

Let op: vanaf Android 13 verschijnt de gekozen taal tevens in de systeeminstellingen onder "Apps", "Biking Pro", "Talen" en kan zij daar eveneens worden gewijzigd. Getalnotaties, datum- en tijdopgaven volgen steeds de actieve taal.

### Over

De knop "Over" opent een dialoogvenster met het versienummer van de geïnstalleerde app en een verwijzing naar deze gebruikershandleiding in het projectarchief op GitHub. De knop "Handleiding openen" in het dialoogvenster opent de pagina in de browser.

## 9. Privacy en gegevensopslag

- Alle ritten, routes en het contactadres worden uitsluitend lokaal opgeslagen, in een database respectievelijk in de app-instellingen van uw apparaat.
- De app heeft geen serververbinding, geen reclame en geen abonnement.
- De kaarttegels worden geladen bij de OpenStreetMap-dienst; daarbij wordt het bij de eerste instelling opgeslagen contactadres als identificatie meegestuurd. Andere persoonsgegevens worden niet overgedragen.
- Wis alle gegevens door in de Android-instellingen onder "Apps", "Biking Pro" de appgegevens te wissen; de vraag naar het contactadres verschijnt daarna opnieuw.

## 10. Veelgestelde vragen en oplossingen

| Waarneming | Oorzaak | Oplossing |
|---|---|---|
| Blauwe stip van de eigen positie ontbreekt | Locatiemachtiging niet verleend of geen GPS-ontvangst | Machtiging verlenen (eenmalig op "Start" drukken of via Android-instellingen, Apps, Biking Pro, Machtigingen); buitenshuis op GPS-ontvangst wachten |
| Kaart start boven Berlijn en springt niet naar mijn positie | Nog geen GPS-fix | Buitenshuis op de eerste GPS-ontvangst wachten; de sprong geschiedt automatisch. Berlijn is slechts een plaatshouder |
| Opname start niet | Locatiedienst van het apparaat uitgeschakeld | Locatiebepaling inschakelen in de snelle Android-instellingen |
| Kaart toont "Access blocked" | Onvoldoende identificatie tegenover OpenStreetMap | Geldig contactadres invoeren; is een verkeerd adres opgeslagen, wis dan de appgegevens en voer het adres opnieuw in |
| Geen melding tijdens de rit (vanaf Android 13) | Meldingsmachtiging geweigerd | Machtiging achteraf verlenen in de Android-instellingen; de opname loopt ook zonder melding |
| GPX-import meldt "GPX-bestand kon niet worden gelezen." | Bestand bevat geen track- of routepunten, of is beschadigd | Ander GPX-bestand gebruiken; het bestand moet trackpunten (trkpt) of routepunten (rtept) bevatten |
| Herstel meldt een ongeldig bestand | Gekozen bestand is geen Biking Pro-back-up | Alleen bestanden gebruiken die via "Instellingen", "Back-up maken" zijn gemaakt |
| De gekozen taal wordt na het herstarten van de app niet opgeslagen (Android 12 en ouder) | Het opslaan van de taalkeuze verloopt intern via een app-mechanisme | De keuze opnieuw maken; de huidige app-versie slaat de keuze permanent op. Blijft het probleem bestaan, werk dan de app bij |
| De app moet weer de apparaattaal volgen | Er is een vaste taal gekozen | In de instellingen onder "Taal kiezen" het item "Systeemtaal" kiezen |
| Snelheid toont onrealistische waarden | GPS-ruis, bijvoorbeeld tussen gebouwen | Voor de beoordeling de gemiddelde waarden hanteren; de minimale snelheid wordt door de app toch pas boven 2 km/u meegerekend |

## 11. Verkrijgbaarheid en versie

De app wordt als installatiebestand (APK) aangeboden via het projectarchief op github.com/Black-Perl/biking-pro. Vóór de installatie moet Android de installatie uit onbekende bronnen toestaan voor uw browser- of bestandsapp. Nieuwe versies worden eenvoudig over de bestaande installatie geïnstalleerd; ritten, routes en het contactad blijven daarbij behouden. Het geïnstalleerde versienummer ziet u op elk moment in het dialoogvenster "Over" in de instellingen van de app; dit dialoogvenster verwijst eveneens naar deze gebruikershandleiding in het projectarchief.

Bij vragen of problemen kunt u zich richten tot het in de publicatie vermelde contactadres van het project.
