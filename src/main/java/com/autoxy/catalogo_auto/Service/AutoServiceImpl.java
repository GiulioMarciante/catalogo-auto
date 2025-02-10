package com.autoxy.catalogo_auto.Service;

import com.autoxy.catalogo_auto.DTO.AutoRequestDTO;
import com.autoxy.catalogo_auto.DTO.AutoResponseDTO;
import com.autoxy.catalogo_auto.Model.Auto;
import com.autoxy.catalogo_auto.Repository.AutoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementazione concreta del servizio per la gestione delle automobili.
 * Utilizza {@link AutoRepository} per l'accesso ai dati e {@link ModelMapper}
 * per la conversione tra Entity e DTO.
 */
@Service
public class AutoServiceImpl implements AutoService{
    @Autowired
    AutoRepository autoRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Recupera tutte le auto convertendole in AutoResponseDTO.
     * @return Lista di tutte le auto disponibili
     */
    @Override
    public List<AutoResponseDTO> findAll() {
        return autoRepository.findAll()
                .stream()
                .map(auto -> modelMapper.map(auto, AutoResponseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Recupera un'auto dal catalogo in base all'ID specificato e la restituisce come {@link AutoResponseDTO}.
     *
     * @param id L'ID dell'auto da cercare. Non deve essere nullo.
     * @return {@link AutoResponseDTO} contenente i dettagli dell'auto trovata.
     * @throws NoSuchElementException Se non esiste un'auto con l'ID specificato.
     */
    @Override
    public AutoResponseDTO findById(Long id) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Auto non trovata con ID: " + id));
        return modelMapper.map(auto, AutoResponseDTO.class);
    }

    /**
     * Salva una nuova auto convertendo l'input da AutoRequestDTO a Entity.
     * @param autoRequestDTO DTO con i dati dell'auto
     * @return AutoResponseDTO con i dati salvati
     */
    @Override
    @Transactional
    public AutoResponseDTO save(AutoRequestDTO autoRequestDTO) {
        Auto auto = modelMapper.map(autoRequestDTO, Auto.class);
        Auto savedAuto = autoRepository.save(auto);
        return modelMapper.map(savedAuto, AutoResponseDTO.class);
    }

    /**
     * Aggiorna un'auto esistente trovandola per ID e applicando i nuovi valori.
     * @param id Identificativo auto da aggiornare
     * @param autoRequestDTO DTO con i nuovi dati
     * @return AutoResponseDTO aggiornata
     * @throws NoSuchElementException se l'auto non esiste
     */
    @Override
    @Transactional
    public AutoResponseDTO update(Long id, AutoRequestDTO autoRequestDTO) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Auto non trovata con ID: " + id));
        modelMapper.map(autoRequestDTO, auto);
        Auto updatedAuto = autoRepository.save(auto);
        return modelMapper.map(updatedAuto, AutoResponseDTO.class);
    }

    /**
     * Elimina un'auto per ID.
     * @param id Identificativo auto da eliminare
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        autoRepository.deleteById(id);
    }
}
