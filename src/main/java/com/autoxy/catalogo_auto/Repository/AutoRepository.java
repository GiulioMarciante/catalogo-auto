package com.autoxy.catalogo_auto.Repository;

import com.autoxy.catalogo_auto.Model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'accesso ai dati delle auto.
 * Estende JpaRepository per operazioni CRUD standard.
 */
@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
}
