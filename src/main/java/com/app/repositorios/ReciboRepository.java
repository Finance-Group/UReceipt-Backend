package com.app.repositorios;

import com.app.entidades.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReciboRepository extends JpaRepository<Recibo,Long> {
    // TODO: find by CCartera
}
