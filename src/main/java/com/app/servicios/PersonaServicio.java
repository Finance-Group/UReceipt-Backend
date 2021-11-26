package com.app.servicios;

import com.app.dto.CambiarContraseniaDto;
import com.app.dto.LoginDto;
import com.app.dto.PersonaDto;
import com.app.entidades.Documento;
import com.app.entidades.Persona;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.DocumentoRepository;
import com.app.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServicio {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Persona Register(PersonaDto personaDto) throws AppException{

        Documento documento = documentoRepository.findById(personaDto.getDocumentoId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","DOCUMENTO-NOENCONTRADO-404"));

        Persona persona = new Persona();
        persona.setId(personaDto.getId());
        persona.setNombre(personaDto.getNombre());
        persona.setRuc(personaDto.getRuc());
        persona.setCorreo(personaDto.getCorreo());
        persona.setPassword(personaDto.getPassword());
        persona.setDocumento(documento);

        return personaRepository.save(persona);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Persona cambiarContrasenia(CambiarContraseniaDto cambiarContraseniaDto) throws AppException{

        Persona persona = personaRepository.findByCorreo(cambiarContraseniaDto.getCorreo())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","PERSONA-NOENCONTRADO-404"));

        persona.setPassword(cambiarContraseniaDto.getPassword());

        return personaRepository.save(persona);
    }

    @Transactional(readOnly = true)
    public Persona loginUser(LoginDto loginDto) throws Exception {
        Persona persona = personaRepository.iniciar(loginDto.getUsername(), loginDto.getPassword())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","PERSONA-NOENCONTRADO-404"));

        return persona;
    }

    @Transactional(readOnly = true)
    public List<Persona> EncontrarUsuarios() {
        return personaRepository.usuarios();
    }

}
