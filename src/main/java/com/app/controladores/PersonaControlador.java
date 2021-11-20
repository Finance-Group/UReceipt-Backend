package com.app.controladores;

import com.app.dto.PersonaDto;
import com.app.entidades.Persona;
import com.app.excepciones.AppException;
import com.app.servicios.PersonaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class PersonaControlador {
    final PersonaServicio personaServicio;


    public PersonaControlador(PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }

    @PostMapping
    public ResponseEntity<String> createUsuario(@RequestBody PersonaDto userDto) throws AppException {
        PersonaDto personaDto = personaServicio.crearUsuario(userDto);

        return new ResponseEntity<>(
                personaDto.getId() + " has been saved as type ", HttpStatus.OK);
    }

}
