package com.app.controladores;

import com.app.common.EntityDtoConverter;
import com.app.dto.DocumentoDto;
import com.app.entidades.Documento;
import com.app.excepciones.AppException;
import com.app.repositorios.DocumentoRepository;
import com.app.servicios.DocumentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documento")
public class DocumentoControlador {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @Autowired
    private DocumentoServicio documentoServicio;

    @PostMapping
    public ResponseEntity<DocumentoDto> crearDocumento(@RequestBody DocumentoDto documentoDto) throws AppException {

        Documento documento = documentoServicio.crearDocumento(documentoDto);

        return new ResponseEntity<>(entityDtoConverter.convertDocumentoToDto(documento), HttpStatus.CREATED);
    }
}
