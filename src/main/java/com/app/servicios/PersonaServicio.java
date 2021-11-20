package com.app.servicios;

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

@Service
public class PersonaServicio {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Persona crearPersona(PersonaDto personaDto) throws AppException{

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

}
