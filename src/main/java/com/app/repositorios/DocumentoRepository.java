package com.app.repositorios;

import com.app.entidades.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento,Long> {
}
