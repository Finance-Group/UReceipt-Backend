package com.app.repositorios;

import com.app.entidades.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonedaRepository extends JpaRepository<Moneda,Long> {
}
