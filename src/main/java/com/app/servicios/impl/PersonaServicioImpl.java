package com.app.servicios.impl;

import com.app.dto.PersonaDto;
import com.app.entidades.Documento;
import com.app.entidades.Persona;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.DocumentoRepository;
import com.app.repositorios.PersonaRepository;
import com.app.servicios.PersonaServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
public class PersonaServicioImpl implements PersonaServicio {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public PersonaDto crearUsuario(PersonaDto personaDto) throws AppException {
        validarUsuario(personaDto);
        Persona persona = new Persona();
        persona.setId(personaDto.getId());
        persona.setNombres(personaDto.getNombres());
        persona.setApellidos(personaDto.getApellidos());
        persona.setRuc(personaDto.getRuc());
        persona.setCorreo(personaDto.getCorreo());
        persona.setPassword(personaDto.getPassword());

        try {
            persona =  personaRepository.save(persona);
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        return modelMapper.map(obtenerPersonaEntity(persona.getId()), PersonaDto.class);
    }

    @Override
    public boolean validarUsuario(PersonaDto personaDto) throws AppException {
        if (personaDto.getNombres().isEmpty() || personaDto.getApellidos().isEmpty() || personaDto.getCorreo().isEmpty() ||
        personaDto.getRuc().toString().isEmpty() || personaDto.getPassword().isEmpty()){
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        return true;
    }
    @Transactional
    @Override
    public void cambiarContraseña(String correo, String contraseña) throws AppException {
        Persona persona = personaRepository.findByCorreo(correo)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","PERSONA-NOENCONTRADO-404"));

        persona.setPassword(contraseña);
        personaRepository.save(persona);
    }

    private Persona obtenerPersonaEntity(Long personaId) throws AppException {
        return personaRepository.findById(personaId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","PERSONA-NOENCONTRADO-404"));
    }
}
