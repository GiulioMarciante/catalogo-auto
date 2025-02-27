Documentazione per Avviare e Testare l'Applicazione

1.Requisiti di Sistema
    Prima di avviare l'applicazione, assicurati di avere installato i seguenti strumenti:
  
    Java Development Kit (JDK) 11 o superiore
  
    Apache Maven 3.6.x o superiore
  
    Database (H2)
  
    Postman o un altro strumento per testare le API REST.

2.Configurazione dell'Ambiente
    Clonare il Repository
    Clona il repository del progetto sul tuo computer:
  
    git clone https://github.com/tuo-repository/catalogo-auto.git
    cd catalogo-auto
  
    Configurare il Database
    configurazione automatica in application.properties

3.Avvio dell'Applicazione 
    Compilare e Avviare l'Applicazione
    Usa Maven per compilare e avviare l'applicazione:
  
    mvn clean install
    mvn spring-boot:run
  
    L'applicazione sarà disponibile all'indirizzo:
    http://localhost:8080

4.Test dell'Applicazione
    Eseguire i Test Unitari
    eseguendo i rispettivi esecutivi ai seguenti Path:
  
    com/autoxy/catalogo_auto/Controller/AutoControllerTest.java
  
    com/autoxy/catalogo_auto/Service/AutoServiceImplTest.java

5.Utilizzo degli Endpoint API
    L'applicazione espone i seguenti endpoint REST per la gestione delle automobili:
  
    Endpoint Disponibili
  
    GET /api/auto	Recupera tutte le auto
  
    GET /api/auto/{id}	Recupera un'auto specifica per ID
  
    POST /api/auto	Crea una nuova auto
  
    PUT /api/auto/{id}	Aggiorna i dati di un'auto esistente
  
    DELETE /api/auto/{id}	Elimina un'auto per ID
  
    GET /api/auto/search Ricerca auto con filtri e paginazione

6.Esempi di Richieste e Risposte
    
  Esempio 1: 
  
  Recuperare tutte le auto
  
  Richiesta (GET /api/auto):
  
  curl -X GET http://localhost:8080/api/auto

   Risposta(200 OK):
  
    [
      {
        "id": 1,
        "marca": "Fiat",
        "modello": "Panda",
        "annoProduzione": 2020,
        "prezzo": 15000.00,
        "stato": "DISPONIBILE"
      },
      {
        "id": 2,
        "marca": "Ford",
        "modello": "Focus",
        "annoProduzione": 2019,
        "prezzo": 18000.00,
        "stato": "VENDUTA"
      }
    ]
    
   Esempio 2: 
   
  Creare una nuova auto
  
  Richiesta (POST /api/auto):
    
      {
      "marca": "Fiat",
      "modello": "Panda",
      "annoProduzione": 2020,
      "prezzo": 15000.00,
      "stato": "DISPONIBILE"
      }
    
  Risposta (200 OK):
  
      {
      "id": 1,
      "marca": "Fiat",
      "modello": "Panda",
      "annoProduzione": 2020,
      "prezzo": 15000.00,
      "stato": "DISPONIBILE"
      }

  Esempio 3: 
  
  Aggiornare un'auto esistente
  
  Richiesta (PUT /api/auto/1):
    
    {
      "marca": "Fiat",
      "modello": "Panda",
      "annoProduzione": 2021,
      "prezzo": 16000.00,
      "stato": "VENDUTA"
    }
      
  Risposta (200 OK):
  
    {
    "id": 1,
    "marca": "Fiat",
    "modello": "Panda",
    "annoProduzione": 2021,
    "prezzo": 16000.00,
    "stato": "VENDUTA"
    }

  Esempio 4: 
  
  Eliminare un'auto
  
  Richiesta (get  /api/auto/1):
  
  curl -X DELETE http://localhost:8080/api/auto/1
      
  Risposta (200 OK):
    
     L'auto con ID 1 è stata eliminata.

  Esempio 5: 
  
  Ricerca di auto con paginazione
  
  Richiesta (GET  /api/auto/search):
  
  curl -X GET "http://localhost:8080/api/auto/search?marca=Fiat&prezzoMin=10000&prezzoMax=20000&page=0&size=2&sort=prezzo,asc"
      
  Risposta (200 OK):
    
       {
    "content": [
      {
        "id": 1,
        "marca": "Fiat",
        "modello": "500",
        "prezzo": 15000,
        "stato": "DISPONIBILE"
      },
      {
        "id": 2,
        "marca": "Fiat",
        "modello": "Panda",
        "prezzo": 18000,
        "stato": "DISPONIBILE"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 2,
      "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": false,
    "totalPages": 3,
    "totalElements": 5,
    "first": true,
    "numberOfElements": 2,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "size": 2,
    "number": 0,
    "empty": false
    }
  
7.Gestione degli Errori
    L'applicazione gestisce i seguenti errori:
  
    404 Not Found: Se l'auto richiesta non esiste.
  
    400 Bad Request: Se i dati inviati non sono validi.
  
    500 Internal Server Error: Se si verifica un errore imprevisto.
