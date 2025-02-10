package com.autoxy.catalogo_auto.Service;

import com.autoxy.catalogo_auto.DTO.AutoRequestDTO;
import com.autoxy.catalogo_auto.DTO.AutoResponseDTO;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service layer per la gestione delle automobili.
 * Offre operazioni CRUD tramite DTO.
 */
public interface AutoService {
    /**
     * Recupera tutte le auto
     * @return Lista di AutoResponseDTO
     */
    List<AutoResponseDTO> findAll();

    /**
     * Cerca un'auto per ID
     * @param id Identificativo dell'auto
     * @return AutoResponseDTO contenente l'auto se trovata
     */
    AutoResponseDTO findById(Long id);

    /**
     * Salva una nuova auto
     * @param autoRequestDTO DTO con i dati dell'auto
     * @return AutoResponseDTO con i dati salvati
     */
    AutoResponseDTO save(AutoRequestDTO autoRequestDTO);

    /**
     * Aggiorna un'auto esistente
     * @param id Identificativo auto da aggiornare
     * @param autoRequestDTO DTO con i nuovi dati
     * @return AutoResponseDTO aggiornata
     * @throws NoSuchElementException se l'auto non esiste
     */
    AutoResponseDTO update(Long id, AutoRequestDTO autoRequestDTO);

    /**
     * Elimina un'auto per ID
     * @param id Identificativo auto da eliminare
     */
    void deleteById(Long id);
}