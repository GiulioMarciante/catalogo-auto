package com.autoxy.catalogo_auto.Model;

import com.autoxy.catalogo_auto.Enum.StatoAuto;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
/**
 * Entità che rappresenta un'automobile nel database.
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Auto {
    /**
     * Identificativo univoco generato automaticamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Marca dell'auto (obbligatoria)
     */
    @Column(nullable = false)
    @NotNull
    private String marca;

    /**
     * Modello dell'auto (obbligatorio)
     */
    @Column(nullable = false)
    @NotNull
    private String modello;

    /**
     * Anno di produzione (≥ 1900)
     */
    @Column(nullable = false)
    @NotNull
    @Min(1900)
    private int annoProduzione;

    /**
     * Prezzo di vendita (≥ 0)
     */
    @Column(nullable = false)
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal prezzo;

    /**
     * Stato corrente dell'auto
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private StatoAuto stato;
}