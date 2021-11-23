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

    @GetMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody LoginDto loginDto)
            throws Exception {
        Boolean estado = personaServicio.loginUser(loginDto.getUsername(), loginDto.getPassword());
        if (estado == true) {
            return new ResponseEntity<>(" login correcto ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(" login incorrecto ", HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonaDto>> EncontrarUsuarios() {
        List<Persona> personas = personaServicio.EncontrarUsuarios();
        return new ResponseEntity<>(entityDtoConverter.convertPersonaToDto(personas), HttpStatus.OK);
    }

}
