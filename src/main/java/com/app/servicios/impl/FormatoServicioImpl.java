package com.app.servicios.impl;

import com.app.dto.CrearFormatoDto;
import com.app.dto.FormatoDto;
import com.app.entidades.Formato;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.FormatoRepository;
import com.app.servicios.FormatoServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormatoServicioImpl implements FormatoServicio {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private FormatoRepository formatoRepository;

    @Transactional
    @Override
    public FormatoDto crearFormato(CrearFormatoDto crearFormatoDto) throws AppException {
        Formato formato = new Formato();
        formato.setNombre(crearFormatoDto.getNombre());

        try {
            formato = formatoRepository.save(formato);
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        return modelMapper.map(obtenerFormatoEntity(formato.getId()), FormatoDto.class);
    }

    @Override
    public FormatoDto obtenerFormatoPorId(Long formatoId) throws AppException {
        Formato formato = formatoRepository.findById(formatoId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","FORMATO-NOENCONTRADO-404"));
        return modelMapper.map(obtenerFormatoEntity(formato.getId()), FormatoDto.class);
    }

    @Override
    public List<FormatoDto> obtenerFormatos() throws AppException {
        List<Formato> formatos = formatoRepository.findAll();
        return formatos.stream().map(product -> modelMapper.map(product, FormatoDto.class)).collect(Collectors.toList());
    }

    private Formato obtenerFormatoEntity(Long formatoId) throws AppException {
        return formatoRepository.findById(formatoId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","FORMATO-NOENCONTRADO-404"));
    }
}
