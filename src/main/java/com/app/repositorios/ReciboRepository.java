package com.app.repositorios;

import com.app.entidades.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReciboRepository extends JpaRepository<Recibo,Long> {
    // TODO: find by CCartera
    List<Recibo> getAllByCarteraId (Long carteraId);
}
