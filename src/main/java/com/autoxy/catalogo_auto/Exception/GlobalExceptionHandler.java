package com.autoxy.catalogo_auto.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Classe che gestisce le eccezioni globali dell'applicazione.
 * Fornisce metodi per gestire eccezioni specifiche e restituire risposte HTTP appropriate.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gestisce le eccezioni di tipo {@link NoSuchElementException}.
     * Questo metodo viene invocato quando un elemento richiesto non viene trovato.
     *
     * @param ex L'eccezione {@link NoSuchElementException} sollevata.
     * @return Una {@link ResponseEntity} con status HTTP 404 (Not Found) e il messaggio dell'eccezione.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Gestisce le eccezioni di tipo {@link MethodArgumentNotValidException}.
     * Questo metodo viene invocato quando i dati inviati nella richiesta non superano la validazione.
     *
     * @param ex L'eccezione {@link MethodArgumentNotValidException} sollevata.
     * @return Una {@link ResponseEntity} con status HTTP 400 (Bad Request) e una mappa di errori,
     *         dove la chiave è il campo non valido e il valore è il messaggio di errore.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Gestisce le eccezioni generiche non gestite da altri handler.
     * Questo metodo viene invocato quando si verifica un'eccezione imprevista.
     *
     * @param ex L'eccezione generica sollevata.
     * @return Una {@link ResponseEntity} con status HTTP 500 (Internal Server Error) e un messaggio generico.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno del server");
    }
}