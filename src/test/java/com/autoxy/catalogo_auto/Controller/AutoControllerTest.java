package com.autoxy.catalogo_auto.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.autoxy.catalogo_auto.DTO.AutoRequestDTO;
import com.autoxy.catalogo_auto.DTO.AutoResponseDTO;
import com.autoxy.catalogo_auto.Service.AutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class AutoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AutoService autoService;

    @InjectMocks
    private AutoController autoController;

    private AutoResponseDTO autoResponseDTO;
    private AutoRequestDTO autoRequestDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(autoController).build();
        autoResponseDTO = new AutoResponseDTO(1L, "Fiat", "Punto", 2010, BigDecimal.valueOf(5000), "DISPONIBILE");
        autoRequestDTO = new AutoRequestDTO("Fiat", "Punto", 2010, BigDecimal.valueOf(5000), "DISPONIBILE");
    }

    @Test
    void getAllAuto_ShouldReturnListOfAutoResponseDTO() throws Exception {
        when(autoService.findAll()).thenReturn(Collections.singletonList(autoResponseDTO));

        mockMvc.perform(get("/api/auto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].marca").value("Fiat"))
                .andExpect(jsonPath("$[0].modello").value("Punto"))
                .andExpect(jsonPath("$[0].annoProduzione").value(2010))
                .andExpect(jsonPath("$[0].prezzo").value(5000))
                .andExpect(jsonPath("$[0].stato").value("DISPONIBILE"));
    }

    @Test
    void getAutoById_ShouldReturnAutoResponseDTO() throws Exception {
        when(autoService.findById(1L)).thenReturn(autoResponseDTO);

        mockMvc.perform(get("/api/auto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.marca").value("Fiat"))
                .andExpect(jsonPath("$.modello").value("Punto"))
                .andExpect(jsonPath("$.annoProduzione").value(2010))
                .andExpect(jsonPath("$.prezzo").value(5000))
                .andExpect(jsonPath("$.stato").value("DISPONIBILE"));
    }

    @Test
    void createAuto_ShouldReturnCreatedAutoResponseDTO() throws Exception {
        when(autoService.save(any(AutoRequestDTO.class))).thenReturn(autoResponseDTO);

        mockMvc.perform(post("/api/auto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(autoRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.marca").value("Fiat"))
                .andExpect(jsonPath("$.modello").value("Punto"))
                .andExpect(jsonPath("$.annoProduzione").value(2010))
                .andExpect(jsonPath("$.prezzo").value(5000))
                .andExpect(jsonPath("$.stato").value("DISPONIBILE"));
    }

    @Test
    void updateAuto_ShouldReturnUpdatedAutoResponseDTO() throws Exception {
        when(autoService.update(eq(1L), any(AutoRequestDTO.class))).thenReturn(autoResponseDTO);

        mockMvc.perform(put("/api/auto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(autoRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.marca").value("Fiat"))
                .andExpect(jsonPath("$.modello").value("Punto"))
                .andExpect(jsonPath("$.annoProduzione").value(2010))
                .andExpect(jsonPath("$.prezzo").value(5000))
                .andExpect(jsonPath("$.stato").value("DISPONIBILE"));
    }

    @Test
    void deleteAuto_ShouldReturnNoContent() throws Exception {
        doNothing().when(autoService).deleteById(1L);

        mockMvc.perform(delete("/api/auto/1"))
                .andExpect(status().isOk());
    }
}