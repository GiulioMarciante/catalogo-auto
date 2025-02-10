package com.autoxy.catalogo_auto.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.autoxy.catalogo_auto.DTO.AutoRequestDTO;
import com.autoxy.catalogo_auto.DTO.AutoResponseDTO;
import com.autoxy.catalogo_auto.Enum.StatoAuto;
import com.autoxy.catalogo_auto.Model.Auto;
import com.autoxy.catalogo_auto.Repository.AutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe di test per {@link AutoServiceImpl} che verifica il corretto funzionamento
 * delle operazioni CRUD sul catalogo auto utilizzando Mockito per il mocking delle dipendenze.
 *
 * <p>Questi test verificano:
 * <ul>
 *   <li>Il recupero di tutte le auto</li>
 *   <li>Il recupero di un'auto per ID</li>
 *   <li>La gestione di errori per ID non trovati</li>
 *   <li>Il salvataggio di una nuova auto</li>
 *   <li>L'aggiornamento di un'auto esistente</li>
 *   <li>L'eliminazione di un'auto</li>
 * </ul>
 *
 * @see AutoServiceImpl
 * @see AutoRepository
 * @see ModelMapper
 */
@ExtendWith(MockitoExtension.class)
public class AutoServiceImplTest {

    @Mock
    private AutoRepository autoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AutoServiceImpl autoService;

    private Auto auto;
    private AutoRequestDTO autoRequestDTO;
    private AutoResponseDTO autoResponseDTO;

    /**
     * Inizializza gli oggetti di test prima di ogni metodo di test.
     * Crea un'istanza di Auto, AutoRequestDTO e AutoResponseDTO con dati di esempio.
     */
    @BeforeEach
    void setUp() {
        auto = new Auto(1L, "Fiat", "Punto", 2010, BigDecimal.valueOf(5000), StatoAuto.DISPONIBILE);
        autoRequestDTO = new AutoRequestDTO("Fiat", "Punto", 2010, BigDecimal.valueOf(5000), "DISPONIBILE");
        autoResponseDTO = new AutoResponseDTO(1L, "Fiat", "Punto", 2010, BigDecimal.valueOf(5000), "DISPONIBILE");
    }

    /**
     * Verifica che il metodo {@link AutoServiceImpl#findAll()} restituisca una lista non vuota
     * di {@link AutoResponseDTO} quando sono presenti auto nel catalogo.
     *
     * <p>Scenario:
     * <ol>
     *   <li>Mock del repository per restituire una lista con un'auto</li>
     *   <li>Mock del ModelMapper per convertire l'entità in DTO</li>
     *   <li>Verifica che la lista restituita abbia dimensione 1</li>
     *   <li>Verifica che i metodi del repository e del ModelMapper siano chiamati correttamente</li>
     * </ol>
     */
    @Test
    void findAll_ShouldReturnListOfAutoResponseDTO() {
        // Given
        when(autoRepository.findAll()).thenReturn(Collections.singletonList(auto));
        when(modelMapper.map(auto, AutoResponseDTO.class)).thenReturn(autoResponseDTO);

        // When
        List<AutoResponseDTO> result = autoService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(autoResponseDTO, result.get(0));

        verify(autoRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(auto, AutoResponseDTO.class);
    }

    /**
     * Verifica che il metodo {@link AutoServiceImpl#findById(Long)} restituisca un
     * {@link AutoResponseDTO} valido quando viene cercato un ID esistente.
     *
     * <p>Scenario:
     * <ol>
     *   <li>Mock del repository per restituire un'auto per l'ID 1L</li>
     *   <li>Verifica che il DTO restituito corrisponda all'auto mockata</li>
     *   <li>Verifica che i metodi del repository e del ModelMapper siano chiamati correttamente</li>
     * </ol>
     */
    @Test
    void findById_ShouldReturnAutoResponseDTO() {
        // Given
        when(autoRepository.findById(1L)).thenReturn(Optional.of(auto));
        when(modelMapper.map(auto, AutoResponseDTO.class)).thenReturn(autoResponseDTO);

        // When
        AutoResponseDTO result = autoService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals(autoResponseDTO, result);

        verify(autoRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(auto, AutoResponseDTO.class);
    }

    /**
     * Verifica che il metodo {@link AutoServiceImpl#save(AutoRequestDTO)} salvi correttamente
     * un'auto e restituisca il {@link AutoResponseDTO} corrispondente.
     *
     * <p>Scenario:
     * <ol>
     *   <li>Mock del ModelMapper per convertire il DTO in entità</li>
     *   <li>Mock del repository per salvare l'entità</li>
     *   <li>Verifica che il DTO restituito corrisponda all'auto mockata</li>
     *   <li>Verifica che i metodi del repository e del ModelMapper siano chiamati correttamente</li>
     * </ol>
     */
    @Test
    void save_ShouldReturnSavedAutoResponseDTO() {
        // Given
        when(modelMapper.map(autoRequestDTO, Auto.class)).thenReturn(auto);
        when(autoRepository.save(auto)).thenReturn(auto);
        when(modelMapper.map(auto, AutoResponseDTO.class)).thenReturn(autoResponseDTO);

        // When
        AutoResponseDTO result = autoService.save(autoRequestDTO);

        // Then
        assertNotNull(result);
        assertEquals(autoResponseDTO, result);

        verify(modelMapper, times(1)).map(autoRequestDTO, Auto.class);
        verify(autoRepository, times(1)).save(auto);
        verify(modelMapper, times(1)).map(auto, AutoResponseDTO.class);
    }

    /**
     * Verifica che il metodo {@link AutoServiceImpl#update(Long, AutoRequestDTO)} aggiorni
     * correttamente un'auto esistente e restituisca il {@link AutoResponseDTO} aggiornato.
     *
     * <p>Scenario:
     * <ol>
     *   <li>Mock del repository per trovare e salvare l'auto</li>
     *   <li>Mock del ModelMapper per aggiornare l'entità e convertirla in DTO</li>
     *   <li>Verifica che il DTO restituito corrisponda all'auto mockata</li>
     *   <li>Verifica che i metodi del repository e del ModelMapper siano chiamati correttamente</li>
     * </ol>
     */
    @Test
    void update_ShouldReturnUpdatedAutoResponseDTO() {
        // Given
        when(autoRepository.findById(1L)).thenReturn(Optional.of(auto));
        when(autoRepository.save(auto)).thenReturn(auto);

        doAnswer(invocation -> {
            AutoRequestDTO source = invocation.getArgument(0);
            Auto destination = invocation.getArgument(1);
            destination.setMarca(source.getMarca());
            destination.setModello(source.getModello());
            destination.setAnnoProduzione(source.getAnnoProduzione());
            destination.setPrezzo(source.getPrezzo());
            destination.setStato(StatoAuto.valueOf(source.getStato()));
            return null;
        }).when(modelMapper).map(eq(autoRequestDTO), eq(auto));

        when(modelMapper.map(auto, AutoResponseDTO.class)).thenReturn(autoResponseDTO);

        // When
        AutoResponseDTO result = autoService.update(1L, autoRequestDTO);

        // Then
        assertNotNull(result);
        assertEquals(autoResponseDTO, result);

        verify(autoRepository, times(1)).findById(1L);
        verify(autoRepository, times(1)).save(auto);
        verify(modelMapper, times(1)).map(autoRequestDTO, auto);
        verify(modelMapper, times(1)).map(auto, AutoResponseDTO.class);
    }

    /**
     * Verifica che il metodo {@link AutoServiceImpl#findById(Long)} sollevi un'eccezione
     * {@link NoSuchElementException} quando viene cercato un ID inesistente.
     *
     * <p>Scenario:
     * <ol>
     *   <li>Mock del repository per restituire Optional.empty()</li>
     *   <li>Verifica che venga sollevata l'eccezione</li>
     *   <li>Verifica che il ModelMapper non venga chiamato</li>
     * </ol>
     *
     * @throws NoSuchElementException quando l'ID non esiste
     */
    @Test
    void findById_ShouldThrowNoSuchElementException() {
        // Given
        when(autoRepository.findById(1L)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(NoSuchElementException.class, () -> autoService.findById(1L));

        verify(autoRepository, times(1)).findById(1L);
        verify(modelMapper, never()).map(any(), any());
    }

    /**
     * Verifica che il metodo {@link AutoServiceImpl#deleteById(Long)} elimini correttamente
     * un'auto tramite il repository.
     *
     * <p>Scenario:
     * <ol>
     *   <li>Mock del repository per eliminare l'auto con ID 1L</li>
     *   <li>Verifica che il metodo deleteById del repository sia chiamato correttamente</li>
     * </ol>
     */
    @Test
    void deleteById_ShouldDeleteAuto() {
        // Given
        doNothing().when(autoRepository).deleteById(1L);

        // When
        autoService.deleteById(1L);

        // Then
        verify(autoRepository, times(1)).deleteById(1L);
    }
}