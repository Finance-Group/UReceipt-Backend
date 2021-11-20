package com.app.common;

import com.app.dto.DocumentoDto;
import com.app.dto.PersonaDto;
import com.app.entidades.Documento;
import com.app.entidades.Persona;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {
    private final ModelMapper modelMapper;

    public EntityDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonaDto convertPersonaToDto(Persona persona) {
        return modelMapper.map(persona, PersonaDto.class);
    }

    public DocumentoDto convertDocumentoToDto(Documento documento) {
        return modelMapper.map(documento, DocumentoDto.class);
    }

}
