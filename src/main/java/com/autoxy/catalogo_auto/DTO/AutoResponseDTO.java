package com.autoxy.catalogo_auto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * DTO per la risposta con i dati dell'auto.
 * Non contiene validatori in quanto usato solo in output.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AutoResponseDTO {
    private Long id;
    private String marca;
    private String modello;
    private int annoProduzione;
    private BigDecimal prezzo;
    private String stato;
}
