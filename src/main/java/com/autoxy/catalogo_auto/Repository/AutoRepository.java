package com.autoxy.catalogo_auto.Repository;

import com.autoxy.catalogo_auto.Enum.StatoAuto;
import com.autoxy.catalogo_auto.Model.Auto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository per l'accesso ai dati delle auto.
 * Estende JpaRepository per operazioni CRUD standard.
 * Fornisce un metodo personalizzato per la ricerca di auto con filtri opzionali.
 */
@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    /**
     * Ricerca auto in base a marca, stato, prezzo minimo e massimo.
     * I parametri sono opzionali: se null, il filtro non viene applicato.
     *
     * @param marca Marca dell'auto (case-insensitive). Se null, ignora il filtro.
     * @param stato Stato dell'auto. Se null, ignora il filtro.
     * @param prezzoMin Prezzo minimo. Se null, ignora il filtro.
     * @param prezzoMax Prezzo massimo. Se null, ignora il filtro.
     * @param pageable Paginazione e ordinamento dei risultati.
     * @return Pagina di risultati contenente le auto che soddisfano i criteri.
     */
    @Query("SELECT a FROM Auto a WHERE " +
            "(:marca IS NULL OR LOWER(a.marca) = LOWER(:marca)) AND " +
            "(:stato IS NULL OR a.stato = :stato) AND " +
            "(:prezzoMin IS NULL OR a.prezzo >= :prezzoMin) AND " +
            "(:prezzoMax IS NULL OR a.prezzo <= :prezzoMax)")
    Page<Auto> search(@Param("marca") String marca,
                      @Param("stato") StatoAuto stato,
                      @Param("prezzoMin") BigDecimal prezzoMin,
                      @Param("prezzoMax") BigDecimal prezzoMax,
                      Pageable pageable);
}
