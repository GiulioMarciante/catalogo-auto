package com.autoxy.catalogo_auto.Controller;

import com.autoxy.catalogo_auto.DTO.AutoRequestDTO;
import com.autoxy.catalogo_auto.DTO.AutoResponseDTO;
import com.autoxy.catalogo_auto.Service.AutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void deleteAuto(@PathVariable Long id) {
        autoService.deleteById(id);
    }
}