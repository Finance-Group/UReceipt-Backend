package com.app.servicios;

import com.app.dto.DocumentoDto;
import com.app.dto.PersonaDto;
import com.app.entidades.Documento;
import com.app.entidades.Persona;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.repositorios.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentoServicio {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Documento crearDocumento(DocumentoDto documentoDto) throws AppException {

        Documento documento = new Documento();

        documento.setNombre(documentoDto.getNombre());


        return documentoRepository.save(documento);
    }
}
