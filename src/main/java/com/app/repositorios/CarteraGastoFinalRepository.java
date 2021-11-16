package com.app.repositorios;

import com.app.entidades.CarteraGastoFinal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarteraGastoFinalRepository extends JpaRepository<CarteraGastoFinal,Long> {
    // TODO: find by CCartera
    List<CarteraGastoFinal> getAllByCarteraId (Long carteraId);
    Optional<CarteraGastoFinal> findByCarteraIdAndGastofinalId (Long carteraId, Long gastoFinalId);
}
