# Sensor Dashboard

Sensor Dashboard è un'applicazione web che consente di visualizzare e monitorare i dati dei sensori in tempo reale. Con Sensor Dashboard, puoi creare una dashboard personalizzata per visualizzare i dati dei sensori da diverse fonti e analizzarli in modo interattivo. L'applicativo è organizzato in più servizi:

- front_end
- fe_api
- sensor_api

NOTA: I sensori sono simulati da un runner che genera i valori di lettura dei sensori registrati.

## FRONT END / DASHBOARD

Questo servizio si occupa di avviare un'applicazione React che fornirà l'accesso alla dashboard per la visualizzazione dello stato e delle statistiche dei sensori e al pannello d'amministrazione per la gestione dei sensori e degli utenti.

### Dipendenze

Questo progetto dipende dalle seguenti librerie e framework:

- `@reduxjs/toolkit`: Una libreria per gestire lo stato dell'applicazione con Redux.
- `axios`: Una libreria per effettuare richieste HTTP.
- `bootstrap` e `bootstrap-icons`: Un framework per creare interfacce utente responsive con componenti predefiniti.
- `chart.js` e `react-chartjs-2`: Una libreria per creare grafici interattivi.
- `ol`: Una libreria per creare mappe interattive.
- `react`, `react-dom` e `react-redux`: Un framework per creare interfacce utente con componenti riutilizzabili.
- `react-router-dom`: Una libreria per gestire la navigazione all'interno di un'applicazione React.
- `redux-persist` e `redux-persist-transform-encrypt`: Librerie per salvare e caricare lo stato dell'applicazione da un archivio locale.

### Script

Il progetto include i seguenti script npm:

- `npm start`: Avvia l'applicazione in modalità sviluppo.
- `npm build`: Crea una versione ottimizzata dell'applicazione per la produzione.
- `npm test`: Esegue i test dell'applicazione.
- `npm eject`: Espone la configurazione di React Scripts per una personalizzazione avanzata.

## Sensor API

Sensor API è un progetto Spring Boot che fornisce un'API per il logging dei dati dei sensori. Questo progetto utilizza la versione 3.1.1 di Spring Boot e richiede Java 17.

### Dipendenze

Questo progetto dipende dalle seguenti librerie e framework:

- `spring-boot-starter-data-jpa`: Una libreria per l'accesso ai dati tramite JPA.
- `spring-boot-starter-security`: Una libreria per la gestione della sicurezza dell'applicazione.
- `spring-boot-starter-web`: Una libreria per creare applicazioni web con Spring MVC.
- `lombok`: Una libreria per ridurre il boilerplate code tramite annotazioni.
- `spring-boot-starter-test` e `spring-security-test`: Librerie per scrivere e eseguire test dell'applicazione.
- `postgresql`: Un driver JDBC per il database PostgreSQL.
- `annotations`: Una libreria di annotazioni di JetBrains.
- `spring-boot-starter-validation`: Una libreria per la validazione dei dati dell'applicazione.
- `spring-websocket` e `spring-messaging`: Librerie per la gestione delle comunicazioni in tempo reale tramite WebSocket.
- `jackson-datatype-jsr310`: Una libreria per la serializzazione e deserializzazione di tipi di data e ora di Java 8.

## Frontend API

Frontend API è un progetto Spring Boot che fornisce un servizio API di accesso per il frontend. Questo progetto utilizza la versione 3.1.1 di Spring Boot e richiede Java 17.

### Dipendenze

Questo progetto dipende dalle seguenti librerie e framework:

- `spring-boot-starter-data-jpa`: Una libreria per l'accesso ai dati tramite JPA.
- `spring-boot-starter-security`: Una libreria per la gestione della sicurezza dell'applicazione.
- `spring-boot-starter-web`: Una libreria per creare applicazioni web con Spring MVC.
- `spring-boot-devtools`: Una libreria per facilitare lo sviluppo di applicazioni Spring Boot.
- `postgresql`: Un driver JDBC per il database PostgreSQL.
- `lombok`: Una libreria per ridurre il boilerplate code tramite annotazioni.
- `spring-boot-starter-test` e `spring-security-test`: Librerie per scrivere e eseguire test dell'applicazione.
- `jjwt-api`, `jjwt-impl` e `jjwt-jackson`: Librerie per la gestione dei token JWT.
- `spring-boot-starter-validation`: Una libreria per la validazione dei dati dell'applicazione.
- `annotations`: Una libreria di annotazioni di JetBrains.
- `spring-boot-starter-websocket`: Una libreria per la gestione delle comunicazioni in tempo reale tramite WebSocket.
- `springdoc-openapi-starter-webmvc-ui`: Una libreria per generare automaticamente la documentazione OpenAPI dell'applicazione.
- `spring-data-commons`: Una libreria per l'accesso ai dati con Spring Data.

## Installazione

Per installare Sensor Dashboard, segui questi passaggi:

1. Prima assicurati di aver installato sul tuo sistema:
   1.1 Postgres Sql
   1.2 Node js
   1.3 Java Temurin v17
   1.4 Maven e configurare i relativi PATH se su OS windows
2. Clona il progetto `git clone https://github.com/12kpro/sensor-dashboard.git`

## Configurazione

1. Successivamente dovrai creare i file `env` nella root dei vari servizi;

   1.1. nella cartella `front_end` dovrai creare un file `.env.local` con il seguente contenuto

   ```
    REACT_APP_SECRET=<Stringa casuale di tua scelta>
    REACT_APP_SRV_URL=http://localhost:5080 //NON cambiare!!
   ```

   1.2. nella cartella `sensor_api` dovrai creare un file `env.properties` con il seguente contenuto:

   ```
   PG_DB=<nome del db>
   PG_PW=<password di accesso a postgres>
   PG_USERNAME=<username di accesso a postgres>
   PORT=5081 //NON cambiare!!
   DEMOSENSOR=true // crea dei sensori dimostrativi
   DEMODATA=true // carica un set di dati di esempio
   AUTHHEADER=X-Shared-Secret
   AUTHSECRET=<stringa casuale di tua scelta>
   ```

   1.4. nella cartella `fe_api` dovrai creare un file `env.properties` con il seguente contenuto:

   ```
   PG_DB=<nome del db>
   PG_PW=<password di accesso a postgres>
   PG_USERNAME=<username di accesso a postgres>
   PORT=5080 //NON cambiare!!
   JWT_SECRET=<stringa casuale di tua scelta>
   JWT_EXPIRATION=7
   BCRYPT_SECRET=<stringa casuale di tua scelta>

   // Dati relativi all' utente amministratore
   NAME=<Nome>
   SURNAME=<Cognome>
   USERNAME=<Nome utente>
   EMAIL=<E-mail>
   PASSWORD=<Password>

   SERVICEKEY=<stringa casuale di tua scelta>
   SERVICEURL=http://localhost:5081 //NON cambiare!!
   ```

### Run

ATTENZIONE, i servizi di backend hanno le CORS attive, quindi assicurati di non cambiare gli URL relativi ai servizi.
Il servizio di frontend è già forzato ad essere eseguito sulla porta 3000, se all'avvio la porta non dovesse essere quella ricevereste un errore di connessione relativo alle CORS.

Per avviare l'applicazione è indispensabile avviare per primo il servizio fe_api, pertanto va aperto un terminale nella cartella fe_api e va dato il comando:

`mvn spring-boot:run`

successivamente è necessario aprire un'altro terminale nella cartella `sensor_api` e dare nuovamente il comando:

`mvn spring-boot:run`

Infine aprendo un altro terminale nella cartella `front_end` dare il comando:

`npm start`

per avviare il frontend dell'applicazione, si aprirà automaticamente il browser predefinito inpostando l'indirizzo su `http://localhost:3000`

### Utilizzo

Per utilizzare Sensor Dashboard, segui questi passaggi:

1. Accedi all'applicazione utilizzando le tue credenziali, al primo avvio sono i dati indicati nel file `env.properties` del servizio `fe_api`
2. Accedi alla sezione Sensori per aggiungere, rimuovere e configurare sensori.
3. Accedi all'area utenti per aggiungere/rimuovere e modificare nuovi utenti.
4. Aggiungi widget alla dashboard accedendo alla sezione profilo e selezionando quali sensori vuoi visualizzare
5. Interagisci con i widget per analizzare i dati in modo interattivo.

NOTA: gli utenti vanno configurati con un ruolo specifico (ADMIN/USER), gli utenti che non hanno ruolo ADMIN non possono accedere alle sezioni Sensori e Utenti!
