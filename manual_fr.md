# Biking Pro - Manuel utilisateur (Français)

État : version 1.2 de l'application. Ce manuel décrit l'ensemble des fonctions de l'application pour l'usage quotidien.

## 1. Présentation

Biking Pro est une application de cyclisme pour Android. Elle enregistre des trajets via le GPS de l'appareil, affiche en direct la vitesse, la distance et la durée, représente l'itinéraire sur une carte OpenStreetMap et gère les trajets ainsi que les routes importées au format GPX. Il n'y a ni publicité, ni abonnement, ni comptes d'utilisateurs ; toutes les données restent sur votre appareil. L'interface est multilingue (allemand, anglais, français, espagnol, danois, néerlandais, italien) ; par défaut, elle suit la langue de l'appareil et peut également être fixée dans les paramètres (voir section 8).

**Configuration requise**

| Élément | Description |
|---|---|
| Système d'exploitation | Android 8.0 ou plus récent |
| Localisation | GPS ou localisation via réseau activée sur l'appareil |
| Autorisations | Position exacte (pour l'enregistrement et l'affichage de la position) ; notifications (à partir d'Android 13, pour l'avis pendant l'enregistrement) |
| Internet | Uniquement pour charger les tuiles de carte d'OpenStreetMap ; l'enregistrement lui-même fonctionne aussi hors ligne |

**Autorisations requises en un coup d'œil**

| Autorisation | Utilité | Quand est-elle demandée |
|---|---|---|
| Position exacte | Enregistrement du trajet, affichage de votre position sur la carte | Au premier appui sur « Start » ; ensuite enregistrée durablement, sauf si vous la révoquez |
| Notifications | Avis permanent « Le trajet est en cours d'enregistrement … » pendant le trajet | À partir d'Android 13, au premier appui sur « Start » |

## 2. Premier démarrage : adresse de contact pour l'utilisation des cartes

Au tout premier démarrage, une boîte de dialogue intitulée « Adresse de contact pour l'utilisation des cartes » apparaît :

1. Les cartes proviennent d'OpenStreetMap. Leurs conditions d'utilisation exigent une adresse de contact, transmise comme identification à chaque demande de carte.
2. Saisissez votre adresse e-mail et choisissez « Enregistrer ».
3. Sans saisie valide, la carte reste verrouillée ; la boîte de dialogue ne peut pas être annulée.

L'adresse est stockée exclusivement en local sur votre appareil, conservée lors des mises à jour de l'application et jamais redemandée ensuite. Elle n'est pas transmise à des tiers ; elle va uniquement au service de cartes OpenStreetMap en tant qu'identification. Après une suppression des données de l'application (voir section 8), la demande réapparaît.

## 3. La page principale en un coup d'œil

La page principale se compose des zones suivantes :

| Zone | Signification |
|---|---|
| Affichage en direct | Vitesse actuelle (km/h), distance (km), durée du trajet, vitesse moyenne et maximale |
| Start / Stop | Démarre et termine l'enregistrement d'un trajet |
| Carte | Carte OpenStreetMap avec votre position, une route planifiée affichée et l'enregistrement en cours |
| La carte suit le trajet | Interrupteur : laissez-le actif et la carte suit automatiquement votre position pendant le trajet |
| Trajets précédents | Ouvre la liste de tous les trajets enregistrés |
| Routes planifiées | Ouvre la gestion des routes avec importation GPX |
| Paramètres | Ouvre la page des paramètres avec sauvegarde/restauration |

La carte prend en charge les gestes habituels : faire glisser pour déplacer, pincer ou écarter deux doigts pour zoomer.

## 4. Enregistrer un trajet

Pour enregistrer un trajet :

1. Appuyez sur « Start ». La toute première fois, l'application demande l'autorisation de localisation (et à partir d'Android 13 également celle des notifications) ; confirmez-les.
2. L'enregistrement continue ensuite aussi en arrière-plan ; une notification permanente « Le trajet est en cours d'enregistrement … » l'indique.
3. L'affichage en direct se met à jour en continu pendant le trajet.
4. À destination, appuyez sur « Stop ». Le trajet est enregistré automatiquement avec toutes les valeurs et apparaît ensuite sous « Trajets précédents ».

**Ce que l'application mesure pendant le trajet**

- Interrogation de la position en mode haute précision, environ toutes les deux secondes.
- Distance, vitesse actuelle, durée du trajet.
- Vitesse moyenne et vitesse maximale.
- Vitesse minimale : seules les valeurs supérieures à 2 km/h sont prises en compte, afin que le bruit GPS à l'arrêt ne produise pas de valeurs absurdes.
- Dénivelé positif maximal : l'application additionne les variations d'altitude positives ; les mètres parcourus en descente ne sont pas soustraits.

**Conseils pratiques**

- Pour obtenir des valeurs exploitables, déplacez-vous en extérieur ; en intérieur, la réception GPS est généralement trop faible.
- Ne laissez pas l'écran se verrouiller par inadvertance pendant que vous observez l'affichage en direct ; l'enregistrement lui-même continue indépendamment.
- En cas de batterie très faible, vous pouvez arrêter l'enregistrement avant la destination ; les données relevées jusque-là sont enregistrées.

## 5. La carte et votre position

- **Votre position :** point bleu à bord blanc ; le cercle semi-transparent autour du point indique la précision GPS actuelle. Le point est également visible en dehors des enregistrements en cours.
- **Première réception GPS :** au démarrage de l'application, la carte se trouve d'abord sur Berlin (placeholder). Dès la première réception GPS, la carte se cale automatiquement une fois sur votre position ; ensuite, elle reste librement utilisable.
- **Route planifiée :** une route sélectionnée est représentée en vert néon sur la carte.
- **Enregistrement en cours :** le trajet en cours est tracé en bleu. Les écarts entre le prévu et le réel sont ainsi directement visibles.
- **La carte suit le trajet :** si l'interrupteur est actif, la vue cartographique suit votre position pendant l'enregistrement.

## 6. Consulter et gérer les trajets précédents

**Liste :** « Trajets précédents » affiche tous les trajets enregistrés, en commençant par le plus récent. Chaque entrée indique la date et l'heure de départ ainsi que la distance, la durée et la vitesse moyenne. Si aucun trajet n'existe encore, le message « Aucun trajet enregistré pour le moment. » apparaît.

**Supprimer un trajet :** l'icône de corbeille à droite de l'entrée ouvre une demande de confirmation ; ce n'est qu'après confirmation avec « Supprimer » que le trajet est définitivement supprimé. L'action est irréversible.

**Page de détail :** un appui sur une entrée de la liste ouvre la vue détaillée avec :

- la carte sur laquelle l'itinéraire est tracé et la vue automatiquement ajustée à l'itinéraire ;
- six valeurs statistiques : distance, durée, vitesse maximale, vitesse minimale, vitesse moyenne et dénivelé positif maximal.

**Export GPX :** le bouton « Exporter en GPX » ouvre la boîte de dialogue système de sélection de fichier. Choisissez un emplacement ; le fichier reçoit automatiquement un nom du type `BikingPro_<date>_<heure>.gpx` et contient l'itinéraire avec tous les points. Vous pouvez ainsi réutiliser le trajet dans d'autres applications de cartes ou d'entraînement.

## 7. Routes planifiées (importation GPX)

**Importer :**

1. Ouvrez « Routes planifiées » et appuyez sur « Importer un fichier GPX ».
2. Choisissez un fichier GPX dans la boîte de dialogue de sélection. Les points de trace (trkpt) comme les points de route (rtept) sont pris en charge.
3. La route apparaît dans la liste. Le nom du fichier GPX est repris ; si le fichier ne contient pas de nom, la route s'appelle « Route sans nom ».

Chaque entrée de la liste indique la longueur de la route et le nombre de points. Si aucune route n'existe encore, le message « Aucune route importée pour le moment. » apparaît.

**Afficher sur la carte :** un appui sur la route l'affiche en vert néon sur la carte principale ; un message confirme la sélection. Revenez à la page principale avec « Retour » : vous verrez la route planifiée et votre propre enregistrement superposés dès que vous démarrez un trajet.

**Effacer la sélection :** le bouton « Effacer la sélection » retire à nouveau la route de la carte principale.

**Renommer :** l'icône de crayon ouvre une boîte de dialogue avec le nom actuel ; après « Enregistrer », la route figure sous son nouveau nom dans la liste.

**Supprimer :** l'icône de corbeille demande confirmation et supprime la route après confirmation. Si la route supprimée est actuellement affichée sur la carte, elle en est automatiquement retirée. Le fichier GPX d'origine sur l'appareil reste inchangé.

## 8. Paramètres

Via le bouton « Paramètres » de la page principale, vous accédez à la page des paramètres. Elle comprend trois zones : « Sauvegarde/Restauration », « Langue » et « À propos ».

### Sauvegarder et restaurer

La sauvegarde regroupe tous les trajets enregistrés et les routes planifiées dans un seul fichier.

**Créer une sauvegarde :**

1. Appuyez sur « Créer une sauvegarde ».
2. La boîte de dialogue système de sélection de fichier s'ouvre ; choisissez un emplacement (par exemple le dossier Téléchargements ou un cloud).
3. Le fichier reçoit automatiquement un nom du type `biking_pro_backup_<date>_<heure>.db`.
4. Un message confirme la fin de l'opération.

**Restaurer une sauvegarde :**

1. Appuyez sur « Restaurer une sauvegarde » et choisissez un fichier précédemment exporté.
2. L'application vérifie d'abord le format du fichier. Seules les sauvegardes valides de Biking Pro sont acceptées ; les fichiers non valides sont rejetés avec un message, sans modifier les données existantes.
3. Après une restauration réussie, tous les trajets et routes contenus sont à nouveau disponibles.

**Remarques importantes :**

- La sauvegarde contient la base de données avec les trajets et les routes. L'adresse de contact pour l'utilisation des cartes n'en fait pas partie ; sur un nouvel appareil, elle est donc redemandée au premier démarrage.
- Lors d'un changement d'appareil, installez d'abord l'application sur le nouvel appareil, restaurez la sauvegarde via « Paramètres » et saisissez ensuite l'adresse de contact.
- Les mises à jour de l'application par-dessus un APK installé conservent vos données ; une sauvegarde constitue la protection supplémentaire contre les dommages, une réinstallation ou un changement d'appareil.
- En outre, l'application crée automatiquement une copie interne de sécurité avant les migrations internes de la base de données. Ce processus n'est pas visible pour vous.

### Langue

La zone « Langue » affiche la langue active de l'application. Par défaut, l'application suit la langue de l'appareil (allemand, anglais, français, espagnol, danois, néerlandais et italien sont pris en charge ; pour toutes les autres langues d'appareil, l'anglais s'applique).

Via le bouton « Choisir la langue », vous pouvez choisir l'une des sept langues prises en charge :

1. Appuyez sur « Choisir la langue » ; une sélection avec la langue du système et toutes les langues prises en charge apparaît.
2. Choisissez une langue ; elle est appliquée immédiatement à toute l'application, sans redémarrage.
3. Le choix est enregistré durablement et survit aux redémarrages de l'application et de l'appareil.
4. L'entrée « Langue du système » rétablit le comportement d'origine : l'application suit de nouveau la langue de l'appareil.

Remarque : à partir d'Android 13, la langue choisie apparaît également dans les paramètres système sous « Applications », « Biking Pro », « Langues » et peut aussi y être modifiée. Les formats de nombres, les dates et les heures suivent toujours la langue active.

### À propos

Le bouton « À propos » ouvre une boîte de dialogue avec le numéro de version de l'application installée et un renvoi vers ce manuel utilisateur dans le dépôt du projet sur GitHub. Le bouton « Ouvrir le manuel » dans la boîte de dialogue ouvre la page dans le navigateur.

## 9. Confidentialité et stockage des données

- Tous les trajets, les routes et l'adresse de contact sont stockés exclusivement en local, dans une base de données ou dans les paramètres de l'application de votre appareil.
- L'application n'a pas de connexion à un serveur, ni publicité, ni abonnement.
- Les tuiles de carte sont chargées depuis le service OpenStreetMap ; l'adresse de contact saisie lors de la configuration initiale est transmise comme identification. Aucune autre donnée personnelle n'est transmise.
- Supprimez toutes les données en supprimant les données de l'application dans les paramètres Android sous « Applications », « Biking Pro » ; la demande d'adresse de contact réapparaît ensuite.

## 10. Questions fréquentes et solutions

| Observation | Cause | Solution |
|---|---|---|
| Le point bleu de ma position manque | Autorisation de localisation non accordée ou pas de réception GPS | Accorder l'autorisation (appuyer une fois sur « Start » ou via Paramètres Android, Applications, Biking Pro, Autorisations) ; attendre la réception GPS en extérieur |
| La carte démarre sur Berlin et ne se cale pas sur ma position | Pas encore de fix GPS | En extérieur, attendre la première réception GPS ; le déplacement s'effectue automatiquement. Berlin n'est qu'un placeholder |
| L'enregistrement ne démarre pas | Service de localisation de l'appareil désactivé | Activer la localisation dans les réglages rapides Android |
| La carte affiche « Access blocked » | Identification insuffisante auprès d'OpenStreetMap | Saisir une adresse de contact valide ; si une adresse erronée a été enregistrée, supprimer les données de l'application et saisir l'adresse à nouveau |
| Pas de notification pendant le trajet (à partir d'Android 13) | Autorisation de notification refusée | Accorder l'autorisation a posteriori dans les paramètres Android ; l'enregistrement fonctionne aussi sans notification |
| L'importation GPX signale « Impossible de lire le fichier GPX. » | Le fichier ne contient aucun point de trace ou de route, ou il est endommagé | Utiliser un autre fichier GPX ; le fichier doit contenir des points de trace (trkpt) ou des points de route (rtept) |
| La restauration signale un fichier non valide | Le fichier choisi n'est pas une sauvegarde de Biking Pro | N'utiliser que des fichiers créés via « Paramètres », « Créer une sauvegarde » |
| La langue choisie n'est pas enregistrée après le redémarrage de l'application (Android 12 et antérieur) | L'enregistrement du choix de langue passe en interne par un mécanisme de l'application | Refaire le choix ; la version actuelle de l'application l'enregistre durablement. Si le problème persiste, mettre à jour l'application |
| L'application doit à nouveau suivre la langue de l'appareil | Une langue fixe a été choisie | Dans les paramètres, sous « Choisir la langue », sélectionner l'entrée « Langue du système » |
| La vitesse affiche des valeurs irréalistes | Bruit GPS, par exemple entre des bâtiments | Utiliser les valeurs moyennes pour l'analyse ; l'application ne prend de toute façon en compte la vitesse minimale qu'au-delà de 2 km/h |

## 11. Mise à disposition et version

L'application est fournie sous forme de fichier d'installation (APK) via le dépôt du projet sur github.com/Black-Perl/biking-pro. Avant l'installation, Android doit autoriser l'installation d'applications de sources inconnues pour votre navigateur ou votre application de fichiers. Les nouvelles versions s'installent simplement par-dessus l'installation existante ; les trajets, les routes et l'adresse de contact sont conservés. Le numéro de version installé est visible à tout moment dans la boîte de dialogue « À propos » des paramètres de l'application ; cette boîte de dialogue renvoie également vers ce manuel dans le dépôt du projet.

En cas de questions ou de problèmes, veuillez vous adresser à l'adresse de contact du projet indiquée dans la publication.
