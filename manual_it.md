# Biking Pro - Manuale utente (Italiano)

Stato: versione 1.3 dell'app. Questo manuale descrive tutte le funzioni dell'app per l'uso quotidiano.

## 1. Di cosa si tratta

Biking Pro è un'app per il ciclismo su Android. Registra le corse tramite il GPS del dispositivo, mostra in tempo reale velocità, distanza e durata, visualizza il percorso su una mappa OpenStreetMap e gestisce le corse e gli itinerari importati in formato GPX. Non ci sono pubblicità, abbonamenti né account utente; tutti i dati restano sul tuo dispositivo. L'interfaccia è multilingue (tedesco, inglese, francese, spagnolo, danese, olandese, italiano); per impostazione predefinita segue la lingua del dispositivo e può anche essere fissata nelle impostazioni (vedi sezione 8).

**Requisiti di sistema**

| Requisito | Descrizione |
|---|---|
| Sistema operativo | Android 8.0 o successivo |
| Localizzazione | GPS o localizzazione basata sulla rete attivata sul dispositivo |
| Autorizzazioni | Posizione esatta (per la registrazione e la visualizzazione della posizione); notifiche (da Android 13, per l'avviso durante la registrazione) |
| Internet | Solo per caricare le sezioni della mappa da OpenStreetMap; la registrazione stessa funziona anche offline |

**Autorizzazioni necessarie in sintesi**

| Autorizzazione | A cosa serve | Quando viene richiesta |
|---|---|---|
| Posizione esatta | Registrazione della corsa, visualizzazione della tua posizione sulla mappa | Alla prima pressione di "Start"; in seguito salvata in modo permanente, salvo revoca |
| Notifiche | Avviso permanente "La corsa viene registrata …" durante la corsa | Da Android 13 alla prima pressione di "Start" |

## 2. Primo avvio: indirizzo di contatto per l'uso delle mappe

Al primo avvio compare una finestra di dialogo con il titolo "Indirizzo di contatto per l'uso delle mappe":

1. Le mappe provengono da OpenStreetMap. Le relative condizioni d'uso richiedono un indirizzo di contatto, trasmesso come identificazione a ogni richiesta di mappa.
2. Inserisci il tuo indirizzo e-mail e scegli "Salva".
3. Senza un inserimento valido la mappa resta bloccata; la finestra di dialogo non può essere annullata.

L'indirizzo viene salvato esclusivamente in locale sul tuo dispositivo, viene mantenuto negli aggiornamenti dell'app e non viene più richiesto. Non viene trasmesso a terzi; arriva solo al servizio di mappe OpenStreetMap come parte dell'identificazione. Dopo l'eliminazione dei dati dell'app (vedi sezione 8), la richiesta compare di nuovo.

## 3. La pagina principale in sintesi

La pagina principale è composta dalle seguenti aree:

| Area | Significato |
|---|---|
| Visualizzazione in tempo reale | Velocità attuale (km/h), distanza (km), durata della corsa, velocità media e massima |
| Start / Stop | Avvia e termina la registrazione di una corsa |
| Mappa | Mappa OpenStreetMap con la tua posizione, un itinerario pianificato visualizzato e la registrazione in corso |
| La mappa segue la corsa | Interruttore: tienilo attivo e la mappa si sposta automaticamente durante la corsa |
| Corse precedenti | Apre l'elenco di tutte le corse salvate |
| Itinerari pianificati | Apre la gestione degli itinerari con importazione GPX |
| Impostazioni | Apre la pagina delle impostazioni con backup/ripristino |

La mappa supporta i gesti consueti: trascinare per spostarla, pizzicare o allargare due dita per lo zoom.

## 4. Registrare una corsa

Per registrare una corsa:

1. Premi "Start". La primissima volta l'app chiede l'autorizzazione di localizzazione (e da Android 13 anche quella di notifica); confermale.
2. La registrazione prosegue anche in background; una notifica permanente con l'avviso "La corsa viene registrata …" lo segnala.
3. La visualizzazione in tempo reale si aggiorna in continuazione durante la corsa.
4. All'arrivo premi "Stop". La corsa viene salvata automaticamente con tutti i valori e compare successivamente in "Corse precedenti".

**Cosa rileva l'app durante la corsa**

- Richiesta della posizione in modalità di alta precisione, circa ogni due secondi.
- Distanza, velocità attuale, durata della corsa.
- Velocità media e velocità massima.
- Velocità minima: solo i valori superiori a 2 km/h vengono considerati, affinché il rumore del GPS da fermo non generi valori privi di senso.
- Dislivello positivo massimo: l'app somma le variazioni di quota positive; i metri percorsi in discesa non vengono sottratti.

**Consigli pratici**

- Per ottenere valori utilizzabili muoviti all'aperto; al chiuso la ricezione GPS è di solito troppo debole.
- Non lasciare che lo schermo si blocchi per sbaglio mentre osservi la visualizzazione in tempo reale; la registrazione stessa prosegue comunque.
- Con la batteria molto scarica puoi terminare la registrazione prima dell'arrivo; i dati rilevati fino a quel momento vengono salvati.

## 5. La mappa e la tua posizione

- **Posizione propria:** punto blu con bordo bianco; il cerchio semitrasparente attorno al punto mostra la precisione GPS attuale. Il punto è visibile anche al di fuori delle registrazioni in corso.
- **Prima ricezione GPS:** all'avvio dell'app la mappa si trova inizialmente su Berlino (segnaposto). Non appena arriva la prima ricezione GPS, la mappa si sposta automaticamente una volta sulla tua posizione; in seguito resta liberamente utilizzabile.
- **Itinerario pianificato:** un itinerario selezionato viene rappresentato in verde neon sulla mappa.
- **Registrazione propria:** la corsa in corso viene disegnata in blu. Le deviazioni tra piano e percorso reale sono così immediatamente visibili.
- **La mappa segue la corsa:** se l'interruttore è attivo, la sezione della mappa segue la tua posizione durante la registrazione.

## 6. Visualizzare e gestire le corse precedenti

**Elenco:** "Corse precedenti" mostra tutte le corse salvate, a partire dalla più recente. Ogni voce indica data e ora di partenza nonché distanza, durata e velocità media. Se non è ancora presente nessuna corsa, compare l'avviso "Ancora nessuna corsa registrata."

**Eliminare una corsa:** il simbolo del cestino a destra della voce apre una richiesta di conferma; solo dopo la conferma con "Elimina" la corsa viene rimossa definitivamente. L'operazione non può essere annullata.

**Pagina di dettaglio:** un tocco su una voce dell'elenco apre la vista di dettaglio con:

- la mappa, sulla quale il percorso è disegnato e la sezione viene impostata automaticamente sul percorso;
- sei valori statistici: distanza, durata, velocità massima, velocità minima, velocità media e dislivello positivo massimo.

**Esportazione GPX:** il pulsante "Esporta come GPX" apre la finestra di selezione file del sistema. Scegli una posizione di salvataggio; il file riceve automaticamente un nome secondo lo schema `BikingPro_<data>_<ora>.gpx` e contiene il percorso con tutti i punti. In questo modo la corsa può essere riutilizzata in altri programmi di mappe o di allenamento.

## 7. Itinerari pianificati (importazione GPX)

**Importare:**

1. Apri "Itinerari pianificati" e premi "Importa file GPX".
2. Scegli un file GPX nella finestra di selezione. Sono supportati sia i punti di traccia (trkpt) sia i punti di itinerario (rtept).
3. L'itinerario compare nell'elenco. Viene adottato il nome del file GPX; se il file non contiene un nome, l'itinerario si chiama "Itinerario senza nome".

Ogni voce dell'elenco mostra la lunghezza dell'itinerario e il numero di punti. Se non è ancora presente nessun itinerario, compare l'avviso "Ancora nessun itinerario importato."

**Mostrare sulla mappa:** un tocco sull'itinerario lo visualizza in verde neon sulla mappa principale; un messaggio conferma la selezione. Torna alla pagina principale con "Indietro" e vedrai l'itinerario pianificato e la tua registrazione sovrapposti non appena avvii una corsa.

**Annulla selezione:** il pulsante "Annulla selezione" rimuove nuovamente l'itinerario dalla mappa principale.

**Rinominare:** il simbolo della matita apre una finestra di dialogo con il nome attuale; dopo "Salva", l'itinerario compare in elenco con il nuovo nome.

**Eliminare:** il simbolo del cestino chiede conferma ed elimina l'itinerario dopo la conferma. Se l'itinerario eliminato è in quel momento visualizzato sulla mappa, viene rimosso automaticamente. Il file GPX originale sul dispositivo resta invariato.

## 8. Impostazioni

Tramite il pulsante "Impostazioni" della pagina principale si accede alla pagina delle impostazioni. Comprende tre aree: "Backup/Ripristino", "Lingua" e "Informazioni".

### Eseguire il backup e ripristinare

Il backup comprende tutte le corse registrate e gli itinerari pianificati in un unico file.

**Crea backup:**

1. Premi "Crea backup".
2. Si apre la finestra di selezione file del sistema; scegli una posizione di salvataggio (ad esempio la cartella download o un cloud).
3. Il file riceve automaticamente un nome secondo lo schema `biking_pro_backup_<data>_<ora>.db`.
4. Un messaggio conferma il completamento.

**Ripristina backup:**

1. Premi "Ripristina backup" e scegli un file esportato in precedenza.
2. L'app verifica innanzitutto il formato del file. Vengono accettati solo backup validi di Biking Pro; i file non validi vengono rifiutati con un messaggio, senza modificare i dati esistenti.
3. Dopo un ripristino riuscito, tutte le corse e gli itinerari contenuti sono di nuovo disponibili.

**Indicazioni importanti:**

- Il backup contiene il database con corse e itinerari. L'indirizzo di contatto per l'uso delle mappe non fa parte del file; su un nuovo dispositivo viene quindi richiesto di nuovo al primo avvio.
- In caso di cambio di dispositivo, installa prima l'app sul nuovo dispositivo, ripristina il backup tramite "Impostazioni" e inserisci poi l'indirizzo di contatto.
- Gli aggiornamenti dell'app su un APK installato conservano in linea di principio i tuoi dati; un backup è la protezione aggiuntiva contro danneggiamenti, reinstallazioni o cambi di dispositivo.
- Inoltre, l'app crea automaticamente una copia interna di sicurezza prima delle migrazioni interne del database. Questo processo non è visibile per te.

### Lingua

L'area "Lingua" mostra la lingua attualmente attiva dell'app. Per impostazione predefinita l'app segue la lingua del dispositivo (sono supportati tedesco, inglese, francese, spagnolo, danese, olandese e italiano; per tutte le altre lingue di sistema si applica l'inglese).

Tramite il pulsante "Scegli lingua" puoi selezionare una delle sette lingue supportate:

1. Premi "Scegli lingua"; compare una selezione con la lingua di sistema e tutte le lingue supportate.
2. Scegli una lingua; viene applicata immediatamente all'intera app, senza riavvio.
3. La scelta viene salvata in modo permanente e sopravvive ai riavvii dell'app e del dispositivo.
4. La voce "Lingua di sistema" ripristina il comportamento originale: l'app segue di nuovo la lingua del dispositivo.

Nota: da Android 13 la lingua scelta compare anche nelle impostazioni di sistema in "App", "Biking Pro", "Lingue" e può essere modificata anche lì. I formati numerici, le date e gli orari seguono sempre la lingua attiva.

### Informazioni

Il pulsante "Informazioni" apre una finestra di dialogo con il numero di versione dell'app installata e un riferimento a questo manuale utente nel repository del progetto su GitHub. Il pulsante "Apri il manuale" nella finestra apre la pagina nel browser.

## 9. Privacy e conservazione dei dati

- Tutte le corse, gli itinerari e l'indirizzo di contatto vengono salvati esclusivamente in locale, in un database oppure nelle impostazioni dell'app del tuo dispositivo.
- L'app non ha connessioni a server, né pubblicità, né abbonamenti.
- Le sezioni della mappa vengono caricate dal servizio OpenStreetMap; l'indirizzo di contatto inserito durante la configurazione iniziale viene trasmesso come identificazione. Nessun altro dato personale viene trasmesso.
- Elimina tutti i dati cancellando i dati dell'app nelle impostazioni di Android in "App", "Biking Pro"; la richiesta dell'indirizzo di contatto compare successivamente di nuovo.

## 10. Domande frequenti e soluzioni

| Osservazione | Causa | Soluzione |
|---|---|---|
| Manca il punto blu della mia posizione | Autorizzazione di localizzazione non concessa o nessuna ricezione GPS | Concedere l'autorizzazione (premere una volta "Start" o via impostazioni Android, App, Biking Pro, Autorizzazioni); attendere la ricezione GPS all'aperto |
| La mappa si avvia su Berlino e non si sposta sulla mia posizione | Ancora nessun fix GPS | All'aperto attendere la prima ricezione GPS; lo spostamento avviene automaticamente. Berlino è solo un segnaposto |
| La registrazione non si avvia | Servizio di localizzazione del dispositivo disattivato | Attivare la localizzazione nelle impostazioni rapide di Android |
| La mappa mostra "Access blocked" | Identificazione insufficiente verso OpenStreetMap | Inserire un indirizzo di contatto valido; se è stato salvato un indirizzo errato, cancellare i dati dell'app e inserire nuovamente l'indirizzo |
| Nessuna notifica durante la corsa (da Android 13) | Autorizzazione di notifica negata | Concedere l'autorizzazione successivamente nelle impostazioni di Android; la registrazione funziona anche senza notifica |
| L'importazione GPX segnala "Impossibile leggere il file GPX." | Il file non contiene punti di traccia né di itinerario, oppure è danneggiato | Utilizzare un altro file GPX; il file deve contenere punti di traccia (trkpt) o punti di itinerario (rtept) |
| Il ripristino segnala un file non valido | Il file scelto non è un backup di Biking Pro | Utilizzare solo file creati tramite "Impostazioni", "Crea backup" |
| La lingua scelta non viene salvata dopo il riavvio dell'app (Android 12 e precedenti) | Il salvataggio della scelta della lingua avviene internamente tramite un meccanismo dell'app | Ripetere la scelta; la versione attuale dell'app la salva in modo permanente. Se il problema persiste, aggiornare l'app |
| L'app deve seguire di nuovo la lingua del dispositivo | È stata selezionata una lingua fissa | Nelle impostazioni, in "Scegli lingua", selezionare la voce "Lingua di sistema" |
| La velocità mostra valori irrealistici | Rumore GPS, ad esempio tra gli edifici | Utilizzare i valori medi per la valutazione; la velocità minima viene comunque considerata dall'app solo sopra i 2 km/h |

## 11. Disponibilità e versione

L'app viene fornita come file di installazione (APK) tramite il repository del progetto su github.com/Black-Perl/biking-pro. Prima dell'installazione Android deve consentire l'installazione da origini sconosciute per la tua app browser o file. Le nuove versioni si installano semplicemente sopra l'installazione esistente; corse, itinerari e indirizzo di contatto vengono conservati. Il numero di versione installato è visibile in ogni momento nella finestra di dialogo "Informazioni" nelle impostazioni dell'app; questa finestra rimanda anche a questo manuale nel repository del progetto.

Per domande o problemi, rivolgersi all'indirizzo di contatto del progetto indicato nella pubblicazione.
