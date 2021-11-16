package com.app.repositorios;

import com.app.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,String> {
    Optional<Persona> findByRuc (Long ruc);
    Optional<Persona> findByCorreo (String correo);
}
