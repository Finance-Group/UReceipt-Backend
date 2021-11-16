package com.app.repositorios;

import com.app.entidades.Dia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaRepository extends JpaRepository<Dia,Long> {
}
