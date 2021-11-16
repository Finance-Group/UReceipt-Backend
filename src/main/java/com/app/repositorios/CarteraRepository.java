package com.app.repositorios;

import com.app.entidades.Cartera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteraRepository extends JpaRepository<Cartera,Long> {
//    TODO: find by NumDocumento
//    TODO: find by NumDocumento and CMoneda
}
