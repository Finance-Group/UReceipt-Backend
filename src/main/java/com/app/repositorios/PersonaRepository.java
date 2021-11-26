package com.app.repositorios;

import com.app.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
//    Optional<Persona> findByRuc (Long ruc);
      @Query(value = "SELECT * FROM persona p WHERE p.NEmail = :correo", nativeQuery = true)
      Optional<Persona> findByCorreo (String correo);

      @Query(value = "SELECT * FROM persona p", nativeQuery = true)
      List<Persona> usuarios();

      @Query(value = "SELECT * FROM persona p WHERE p.num_documento =:username AND p.NPassword =:contra", nativeQuery = true)
      Optional<Persona> iniciar(Long username, String contra);
}
