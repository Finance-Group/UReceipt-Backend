package com.app.repositorios;

import com.app.entidades.CarteraGastoInicial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteraGastoInicialRepository extends JpaRepository<CarteraGastoInicial,Long> {
    // TODO: find by CCartera
}
