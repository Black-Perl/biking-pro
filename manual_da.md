# Biking Pro - Brugervejledning (Dansk)

Stand: app-version 1.3. Denne manual beskriver alle appens funktioner til daglig brug.

## 1. Hvad det handler om

Biking Pro er en cykelapp til Android. Den optager ture via enhedens GPS, viser hastighed, distance og kørselstid live, viser ruten på et OpenStreetMap-kort og administrerer ture såvel som importerede GPX-ruter. Der er ingen reklamer, intet abonnement og ingen brugerkonti; alle data forbliver på din enhed. Brugerfladen er flersproget (tysk, engelsk, fransk, spansk, dansk, hollandsk, italiensk); som standard følger den enhedens sprog og kan også vælges fast i indstillingerne (se afsnit 8).

**Systemkrav**

| Krav | Beskrivelse |
|---|---|
| Styresystem | Android 8.0 eller nyere |
| Lokalisering | GPS eller netværksbaseret lokalisering aktiveret på enheden |
| Tilladelser | Nøjagtig placering (til optagelse og positionsvisning); meddelelser (fra Android 13, til meddelelsen under optagelsen) |
| Internet | Kun til at hente kortfelter fra OpenStreetMap; selve optagelsen fungerer også offline |

**Nødvendige tilladelser med et overblik**

| Tilladelse | Til hvad | Hvornår spørges der |
|---|---|---|
| Nøjagtig placering | Optagelse af kørslen, visning af din egen position på kortet | Ved første tryk på "Start"; derefter gemt permanent, medmindre du tilbagekalder den |
| Meddelelser | Permanent meddelelse "Kørslen optages …" under kørslen | Fra Android 13 ved første tryk på "Start" |

## 2. Første start: kontaktadresse til kortanvendelse

Ved den allerførste start vises en dialog med titlen "Kontaktadresse til kortanvendelse":

1. Kortene kommer fra OpenStreetMap. Deres anvendelsesvilkår kræver en kontaktadresse, som overføres som identifikation ved hver kortanmodning.
2. Indtast din e-mailadresse og vælg "Gem".
3. Uden en gyldig indtastning forbliver kortet låst; dialogen kan ikke annulleres.

Adressen gemmes udelukkende lokalt på din enhed, bevares ved appopdateringer og spørges aldrig om igen. Den videregives ikke til tredjeparter; den går kun som en del af identifikationen til OpenStreetMap-korttjenesten. Når appdata slettes (se afsnit 8), vises forespørgslen igen.

## 3. Hovedsiden med et overblik

Hovedsiden består af følgende områder:

| Område | Betydning |
|---|---|
| Livevisning | Aktuel hastighed (km/t), distance (km), kørselstid, gennemsnits- og tophastighed |
| Start / Stop | Starter og afslutter optagelsen af en tur |
| Kort | OpenStreetMap-kort med din egen position, en vist planlagt rute og den igangværende optagelse |
| Kortet følger kørslen | Kontakt: hold den aktiv, og kortet flytter automatisk med under kørslen |
| Tidligere ture | Åbner listen over alle gemte ture |
| Planlagte ruter | Åbner ruteadministratoren med GPX-import |
| Indstillinger | Åbner indstillingssiden med sikkerhedskopi/gendannelse |

Kortet understøtter de sædvanlige bevægelser: træk for at flytte, knib eller spred to fingre for at zoome.

## 4. Optage en tur

Sådan optager du en tur:

1. Tryk på "Start". Den allerførste gang spørger appen om placerings-tilladelsen (og fra Android 13 også meddelelsestilladelsen); bekræft disse.
2. Optagelsen fortsætter nu også i baggrunden; en permanent meddelelse med bemærkningen "Kørslen optages …" gør opmærksom på det.
3. Livevisningen opdateres løbende under kørslen.
4. Tryk på "Stop" ved målet. Turen gemmes automatisk med alle værdier og vises derefter under "Tidligere ture".

**Hvad appen registrerer under kørslen**

- Positionsforespørgsel i tilstanden høj præcision, omtrent hvert andet sekund.
- Distance, aktuel hastighed, kørselstid.
- Gennemsnits- og tophastighed.
- Minimumshastighed: kun værdier over 2 km/t indgår, så GPS-støj i ståstilstand ikke giver meningsløse værdier.
- Maksimal stigning: appen lægger positive højdeændringer sammen; meter kørt nedad bakke trækkes ikke fra.

**Praktiske tips**

- For brugbare værdier bør du bevæge dig udendørs; indendørs er GPS-modtagelsen som regel for svag.
- Undgå at lade skærmen låse ved en fejl, mens du følger livevisningen; selve optagelsen fortsætter uanset det.
- Ved meget lav batteri kan du afslutte optagelsen før målet; de data, der er registreret indtil da, gemmes.

## 5. Kortet og din egen position

- **Egen position:** blå prik med hvid kant; den halvgennemsigtige cirkel omkring prikken viser den aktuelle GPS-nøjagtighed. Prikken er også synlig uden for igangværende optagelser.
- **Første GPS-modtagelse:** ved appens start ligger kortet først over Berlin (pladsholder). Så snart der er GPS-modtagelse første gang, springer kortet automatisk én gang til din position; derefter forbliver det frit betjeningsmuligt.
- **Planlagt rute:** en valgt rute vises i neon-grøn på kortet.
- **Egen optagelse:** den igangværende tur tegnes i blåt. Afvigelser mellem plan og virkelighed er dermed direkte synlige.
- **Kortet følger kørslen:** er kontakten aktiv, følger kortudsnittet din position under optagelsen.

## 6. Se og administrere tidligere ture

**Liste:** "Tidligere ture" viser alle gemte ture, begyndende med den nyeste. Hver post angiver dato og starttidspunkt samt distance, varighed og gennemsnitshastighed. Findes der endnu ingen tur, vises bemærkningen "Endnu ingen optagede ture."

**Slette en tur:** papirkurvsymbolet i højre side af posten åbner en sikkerhedsforespørgsel; først efter bekræftelse med "Slet" fjernes turen permanent. Handlingen kan ikke fortrydes.

**Detaljeside:** et tryk på en post på listen åbner detaljevisningen med:

- kortet, hvor ruten er tegnet, og udsnittet automatisk indstilles til ruten;
- seks statistikværdier: distance, kørselstid, tophastighed, minimumshastighed, gennemsnitshastighed og maksimal stigning.

**GPX-eksport:** knappen "Eksportér som GPX" åbner systemets filvalgsdialog. Vælg et gemmested; filen får automatisk et navn efter mønsteret `BikingPro_<dato>_<tid>.gpx` og indeholder ruten med alle punkter. Dermed kan turen genbruges i andre kort- eller træningsprogrammer.

## 7. Planlagte ruter (GPX-import)

**Importere:**

1. Åbn "Planlagte ruter", og tryk på "Importér GPX-fil".
2. Vælg en GPX-fil i filvalgsdialogen. Både track-punkter (trkpt) og rutepunkter (rtept) understøttes.
3. Ruten vises på listen. Navnet fra GPX-filen overtages; indeholder filen intet navn, kaldes ruten "Rute uden navn".

Hver post på listen viser rutens længde og antallet af punkter. Findes der endnu ingen rute, vises bemærkningen "Endnu ingen importerede ruter."

**Vise på kortet:** et tryk på ruten viser den i neon-grøn på hovedkortet; en meddelelse bekræfter valget. Gå tilbage til hovedsiden med "Tilbage"; dér ser du den planlagte rute og din egen optagelse oven på hinanden, så snart du starter en tur.

**Ryd valg:** knappen "Ryd valg" fjerner ruten fra hovedkortet igen.

**Omdøbe:** blyantsymbolet åbner en dialog med det aktuelle navn; efter "Gem" findes ruten under det nye navn på listen.

**Slette:** papirkurvsymbolet spørger for en sikkerheds skyld og sletter ruten efter bekræftelse. Er den slettede rute netop vist på kortet, fjernes den automatisk dér. Den oprindelige GPX-fil på enheden forbliver upåvirket.

## 8. Indstillinger

Via knappen "Indstillinger" på hovedsiden kommer du til indstillingssiden. Den omfatter tre områder: "Sikkerhedskopi/Gendannelse", "Sprog" og "Om".

### Sikkerhedskopiere og gendanne

Sikkerhedskopien indeholder alle optagede ture og planlagte ruter som én enkelt fil.

**Opret sikkerhedskopi:**

1. Tryk på "Opret sikkerhedskopi".
2. Systemets filvalgsdialog åbnes; vælg et gemmested (f.eks. downloadmappen eller en cloud).
3. Filen får automatisk et navn efter mønsteret `biking_pro_backup_<dato>_<tid>.db`.
4. En meddelelse bekræfter gennemførelsen.

**Gendan sikkerhedskopi:**

1. Tryk på "Gendan sikkerhedskopi", og vælg en tidligere eksporteret fil.
2. Appen undersøger først filformatet. Kun gyldige Biking Pro-sikkerhedskopier overføres; ugyldige filer afvises med en meddelelse uden at ændre de eksisterende data.
3. Efter en vellykket gendannelse er alle indeholdte ture og ruter atter til rådighed.

**Vigtige bemærkninger:**

- Sikkerhedskopien indeholder databasen med ture og ruter. Kontaktadressen til kortanvendelse er ikke en del af filen; på en ny enhed spørges der derfor om den igen ved første start.
- Ved enhedsskift installerer du først appen på den nye enhed, gendanner sikkerhedskopien via "Indstillinger" og indtaster derefter kontaktadressen.
- Appopdateringer oven på en installeret APK bevarer principielt dine data; en sikkerhedskopi er den ekstra beskyttelse mod beskadigelse, ny installation eller enhedsskift.
- Derudover opretter appen automatisk en intern sikkerhedskopi før interne databasemigreringer. Denne proces er ikke synlig for dig.

### Sprog

Området "Sprog" viser det aktuelt aktive appsprog. Som standard følger appen enhedens sprog (understøttet er tysk, engelsk, fransk, spansk, dansk, hollandsk og italiensk; ved alle andre enhedssprog anvendes engelsk).

Via knappen "Vælg sprog" kan du i stedet vælge et af de syv understøttede sprog:

1. Tryk på "Vælg sprog"; der vises en oversigt med systemsproget og alle understøttede sprog.
2. Vælg et sprog; det anvendes straks i hele appen, uden genstart.
3. Valget gemmes permanent og overlever genstarter af appen og enheden.
4. Posten "Systemsprog" gendanner den oprindelige adfærd: Appen følger igen enhedens sprog.

Bemærk: Fra Android 13 vises det valgte sprog tillige i systemindstillingerne under "Apps", "Biking Pro", "Sprog" og kan også ændres dér. Talformater, datoer og klokkeslæt følger altid det aktive sprog.

### Om

Knappen "Om" åbner en dialog med versionsnummeret på den installerede app og en henvisning til denne brugervejledning i projektarkivet på GitHub. Knappen "Åbn manualen" i dialogen åbner siden i browseren.

## 9. Databeskyttelse og datalagring

- Alle ture, ruter og kontaktadressen gemmes udelukkende lokalt i en database henholdsvis i appens indstillinger på din enhed.
- Appen har ingen serverforbindelse, ingen reklamer og intet abonnement.
- Kortfelterne hentes fra OpenStreetMap-tjenesten; kontaktadressen, der blev lagret under den første opsætning, overføres som identifikation. Andre personlige data overføres ikke.
- Slet alle data ved at slette appdata under "Apps", "Biking Pro" i Android-indstillingerne; forespørgslen om kontaktadressen vises derefter igen.

## 10. Hyppige spørgsmål og løsninger

| Iagttagelse | Årsag | Løsning |
|---|---|---|
| Den blå prik for min position mangler | Placerings-tilladelse ikke givet, eller der er ingen GPS-modtagelse | Giv tilladelsen (tryk én gang på "Start", eller via Android-indstillinger, Apps, Biking Pro, Tilladelser); vent på GPS-modtagelse udendørs |
| Kortet starter over Berlin og springer ikke til min position | Endnu ingen GPS-fix | Vent udendørs på den første GPS-modtagelse; springet sker automatisk. Berlin er kun en pladsholder |
| Optagelsen starter ikke | Enhedens lokaliseringstjeneste er deaktiveret | Aktivér lokalisering i Androids hurtige indstillinger |
| Kortet viser "Access blocked" | Utilstrækkelig identifikation over for OpenStreetMap | Indtast en gyldig kontaktadresse; blev en forkert adresse gemt, så slet appdata og indtast adressen igen |
| Ingen meddelelse under kørslen (fra Android 13) | Meddelelsestilladelse afvist | Giv tilladelsen efterfølgende i Android-indstillingerne; optagelsen kører også uden meddelelse |
| GPX-importen melder "GPX-filen kunne ikke læses." | Filen indeholder hverken track- eller rutepunkter, eller den er beskadiget | Brug en anden GPX-fil; filen skal indeholde track-punkter (trkpt) eller rutepunkter (rtept) |
| Gendannelse melder om en ugyldig fil | Den valgte fil er ikke en Biking Pro-sikkerhedskopi | Brug kun filer, der er oprettet via "Indstillinger", "Opret sikkerhedskopi" |
| Det valgte sprog gemmes ikke efter genstart af appen (Android 12 og ældre) | Gemningen af sprogvalget kører internt via en app-mekanisme | Foretag valget igen; den aktuelle appversion gemmer valget permanent. Består problemet, så opdatér appen |
| Appen skal igen følge enhedens sprog | Der er valgt et fast sprog | Vælg posten "Systemsprog" under "Vælg sprog" i indstillingerne |
| Hastigheden viser urealistiske værdier | GPS-støj, f.eks. mellem huse | Anvend gennemsnitsværdierne til vurderingen; minimumshastigheden tager appen i forvejen kun højde for over 2 km/t |

## 11. Tilgængelighed og version

Appen udbydes som installationsfil (APK) via projektarkivet på github.com/Black-Perl/biking-pro. Før installationen skal Android tillade installation fra ukendte kilder for din browser- eller fil-app. Nye versioner installeres blot oven på den eksisterende installation; ture, ruter og kontaktadressen bevares. Det installerede versionsnummer kan du til enhver tid se i dialogen "Om" i appens indstillinger; denne dialog henviser også til denne brugervejledning i projektarkivet.

Ved spørgsmål eller problemer bedes du henvende dig til projektets kontaktadresse, der er angivet i udgivelsen.
