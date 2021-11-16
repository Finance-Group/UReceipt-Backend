package com.app.repositorios;

import com.app.entidades.CarteraGastoFinal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteraGastoFinalRepository extends JpaRepository<CarteraGastoFinal,Long> {
    // TODO: find by CCartera
}
