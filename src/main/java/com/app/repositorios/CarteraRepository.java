package com.app.repositorios;

import com.app.entidades.Cartera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteraRepository extends JpaRepository<Cartera,Long> {
//    TODO: get all by NumDocumento
    List<Cartera> getAllByPersonaId (Long personaId);
//    TODO: get all by NumDocumento and CMoneda
    List<Cartera> getAllByPersonaIdAndMonedaId (Long personaId, Long monedaId);
}
