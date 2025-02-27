package com.autoxy.catalogo_auto.Controller;

import com.autoxy.catalogo_auto.DTO.AutoRequestDTO;
import com.autoxy.catalogo_auto.DTO.AutoResponseDTO;
import com.autoxy.catalogo_auto.Enum.StatoAuto;
import com.autoxy.catalogo_auto.Service.AutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Controller REST per la gestione delle automobili.
 * Espone endpoints CRUD per operazioni su auto, accessibili tramite il percorso base `/api/auto`.
 * Tutti i metodi restituiscono DTO come risposta.
 */
@RestController
@RequestMapping("api/auto")
public class AutoController {

    @Autowired
    private AutoService autoService;

    /**
     * Recupera tutte le auto disponibili.
     *
     * @return Una lista di {@link AutoResponseDTO} rappresentanti tutte le auto
     * @apiNote GET /api/auto
     */
    @GetMapping
    @Operation(summary = "Ottiene la lista di tutte le auto")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })

    public List<AutoResponseDTO> getAllAuto() {
        return autoService.findAll();
    }

    /**
     * Recupera un'auto specifica tramite il suo ID.
     *
     * @param id Identificativo univoco dell'auto
     * @return Un {@link Optional} contenente l'{@link AutoResponseDTO} se l'auto è trovata,
     * altrimenti un Optional vuoto
     * @apiNote GET /api/auto/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Ottieni auto per ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public AutoResponseDTO getAutoById(@PathVariable Long id) {
        return autoService.findById(id);
    }

    /**
     * Crea una nuova auto.
     *
     * @param autoRequestDTO DTO contenente i dati della nuova auto
     * @return L'{@link AutoResponseDTO} dell'auto creata
     * @apiNote POST /api/auto
     */
    @PostMapping
    @Operation(summary = "Creazione nuova auto")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public AutoResponseDTO createAuto(@Valid @RequestBody AutoRequestDTO autoRequestDTO) {
        return autoService.save(autoRequestDTO);
    }

    /**
     * Aggiorna i dati di un'auto esistente.
     *
     * @param id Identificativo univoco dell'auto da aggiornare
     * @param autoRequestDTO DTO contenente i nuovi dati dell'auto
     * @return L'{@link AutoResponseDTO} dell'auto aggiornata
     * @throws NoSuchElementException Se l'auto con l'ID specificato non esiste
     * @apiNote PUT /api/auto/{id}
     */
    @PutMapping("/{id}")
    @Operation(summary = "Modifica dei valori dell'auto attraverso ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public AutoResponseDTO updateAuto(@PathVariable Long id, @Valid @RequestBody AutoRequestDTO autoRequestDTO) {
        return autoService.update(id, autoRequestDTO);
    }

    /**
     * Elimina un'auto tramite il suo ID.
     *
     * @param id Identificativo univoco dell'auto da eliminare
     * @apiNote DELETE /api/auto/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminazione di un auto attraverso ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public void deleteAuto(@PathVariable Long id) {
        autoService.deleteById(id);
    }

    /**
     * Endpoint per la ricerca di auto con filtri opzionali.
     *
     * @param marca Marca dell'auto (case-insensitive). Se null, ignora il filtro.
     * @param prezzoMin Prezzo minimo. Se null, ignora il filtro.
     * @param prezzoMax Prezzo massimo. Se null, ignora il filtro.
     * @param stato Stato dell'auto. Se null, ignora il filtro.
     * @param pageable Paginazione e ordinamento dei risultati.
     * @return Pagina di risultati contenente DTO delle auto che soddisfano i criteri.
     */
    @GetMapping("/search")
    @Operation(summary = "Ricerca auto con parametri opzionali marca,prezzoMin,prezzoMax,stato con Pageable")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public Page<AutoResponseDTO> searchAuto(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) BigDecimal prezzoMin,
            @RequestParam(required = false) BigDecimal prezzoMax,
            @RequestParam(required = false) StatoAuto stato,
            Pageable pageable) {

        return autoService.search(marca, prezzoMin, prezzoMax, stato, pageable);
    }
}