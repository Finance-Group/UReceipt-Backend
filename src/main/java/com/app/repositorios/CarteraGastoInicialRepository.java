package com.app.repositorios;

import com.app.entidades.CarteraGastoInicial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarteraGastoInicialRepository extends JpaRepository<CarteraGastoInicial,Long> {
    // TODO: find by CCartera
    List<CarteraGastoInicial> getAllByCarteraId (Long carteraId);
    Optional<CarteraGastoInicial> findByCarteraIdAndGastoinicialId (Long carteraId, Long gastoInicialId);
}
