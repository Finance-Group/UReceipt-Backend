package com.app.repositorios;

import com.app.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
//    Optional<Persona> findByRuc (Long ruc);
//    Optional<Persona> findByCorreo (String correo);
}
