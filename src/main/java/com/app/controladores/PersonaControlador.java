package com.app.controladores;

import com.app.common.EntityDtoConverter;
import com.app.dto.CambiarContraseniaDto;
import com.app.dto.LoginDto;
import com.app.dto.PersonaDto;
import com.app.entidades.Persona;
import com.app.excepciones.AppException;
import com.app.servicios.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaControlador {

    private final EntityDtoConverter entityDtoConverter;
    final PersonaServicio personaServicio;

    public PersonaControlador(PersonaServicio personaServicio, EntityDtoConverter entityDtoConverter) {
        this.personaServicio = personaServicio;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping("/registro")
    public ResponseEntity<PersonaDto> RegistroUsuario(@RequestBody PersonaDto personaDto) throws AppException {

        Persona persona = personaServicio.Register(personaDto);

        return new ResponseEntity<>(entityDtoConverter.convertPersonaToDto(persona), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonaDto> cambiarContrasenia(@RequestBody CambiarContraseniaDto cambiarContraseniaDto) throws AppException {
        Persona persona= personaServicio.cambiarContrasenia(cambiarContraseniaDto);
        return new ResponseEntity<>(entityDtoConverter.convertPersonaToDto(persona), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<PersonaDto> loginUsuario(@RequestBody LoginDto loginDto) throws Exception {
        Persona persona = personaServicio.loginUser(loginDto);

        return new ResponseEntity<>(entityDtoConverter.convertPersonaToDto(persona), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<PersonaDto>> EncontrarUsuarios() {
        List<Persona> personas = personaServicio.EncontrarUsuarios();
        return new ResponseEntity<>(entityDtoConverter.convertPersonaToDto(personas), HttpStatus.OK);
    }

}
