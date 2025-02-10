Documentazione per Avviare e Testare l'Applicazione
1. Requisiti di Sistema
Prima di avviare l'applicazione, assicurati di avere installato i seguenti strumenti:

Java Development Kit (JDK) 11 o superiore

Apache Maven 3.6.x o superiore

Database (H2)

Postman o un altro strumento per testare le API REST.

2. Configurazione dell'Ambiente
Clonare il Repository
Clona il repository del progetto sul tuo computer:

git clone https://github.com/tuo-repository/catalogo-auto.git
cd catalogo-auto

Configurare il Database
configurazione automatica in application.properties

3. Avvio dell'Applicazione 
Compilare e Avviare l'Applicazione
Usa Maven per compilare e avviare l'applicazione:

mvn clean install
mvn spring-boot:run

L'applicazione sarà disponibile all'indirizzo:
http://localhost:8080

4. Test dell'Applicazione
Eseguire i Test Unitari
eseguendo i rispettivi esecutivi ai seguenti Path:

com/autoxy/catalogo_auto/Controller/AutoControllerTest.java

com/autoxy/catalogo_auto/Service/AutoServiceImplTest.java

5. Utilizzo degli Endpoint API
L'applicazione espone i seguenti endpoint REST per la gestione delle automobili:

Endpoint Disponibili

GET /api/auto	Recupera tutte le auto

GET	/api/auto/{id}	Recupera un'auto specifica per ID

POST/api/auto	Crea una nuova auto

PUT	/api/auto/{id}	Aggiorna i dati di un'auto esistente

DELETE	/api/auto/{id}	Elimina un'auto per ID

GET /api/auto/search Ricerca auto con filtri e paginazione

6. Esempi di Richieste e Risposte
Esempio 1: Creare una nuova auto
Richiesta (POST /api/auto):
{
  "marca": "Fiat",
  "modello": "Panda",
  "annoProduzione": 2020,
  "prezzo": 15000.00,
  "stato": "DISPONIBILE"
}
7. Gestione degli Errori
L'applicazione gestisce i seguenti errori:

404 Not Found: Se l'auto richiesta non esiste.

400 Bad Request: Se i dati inviati non sono validi.

500 Internal Server Error: Se si verifica un errore imprevisto.
