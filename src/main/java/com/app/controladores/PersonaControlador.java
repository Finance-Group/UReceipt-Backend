package com.app.controladores;

import com.app.common.EntityDtoConverter;
import com.app.dto.PersonaDto;
import com.app.entidades.Persona;
import com.app.excepciones.AppException;
import com.app.servicios.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonaControlador {

    private final EntityDtoConverter entityDtoConverter;
    final PersonaServicio personaServicio;

    public PersonaControlador(PersonaServicio personaServicio, EntityDtoConverter entityDtoConverter) {
        this.personaServicio = personaServicio;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    public ResponseEntity<PersonaDto> crearUsuario(@RequestBody PersonaDto personaDto) throws AppException {

        Persona persona = personaServicio.crearPersona(personaDto);

        return new ResponseEntity<>(entityDtoConverter.convertPersonaToDto(persona), HttpStatus.CREATED);
    }

}
