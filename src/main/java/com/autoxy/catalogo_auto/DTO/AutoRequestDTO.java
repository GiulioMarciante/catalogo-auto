package com.autoxy.catalogo_auto.DTO;

import com.autoxy.catalogo_auto.Enum.StatoAuto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * DTO per la richiesta di creazione/modifica auto.
 * Contiene i validatori per l'input.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AutoRequestDTO {
    @NotBlank(message = "La marca è obbligatoria")
    private String marca;

    @NotBlank(message = "Il modello è obbligatorio")
    private String modello;

    @NotNull
    @Min(value = 1900, message = "L'anno di produzione deve essere maggiore di 1900")
    private int annoProduzione;

    @NotNull
    @DecimalMin(value = "0.0", message = "Il prezzo deve essere maggiore o uguale a 0")
    private BigDecimal prezzo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Lo stato è obbligatorio")
    @Pattern(regexp = "DISPONIBILE|VENDUTA", message = "Lo stato deve essere 'DISPONIBILE' o 'VENDUTA'")
    private String stato;
}
