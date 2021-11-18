package com.app.servicios.impl;

import com.app.dto.CrearGastoFinalDto;
import com.app.dto.GastoFinalDto;
import com.app.entidades.GastoFinal;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.GastoFinalRepository;
import com.app.servicios.GastoFinalServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GastoFinalServicioImpl implements GastoFinalServicio {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private GastoFinalRepository gastoFinalRepository;

    @Transactional
    @Override
    public GastoFinalDto crearGastoFinal(CrearGastoFinalDto crearGastoFinalDto) throws AppException {
        GastoFinal gastoFinal = new GastoFinal();
        gastoFinal.setNombre(crearGastoFinalDto.getNombre());

        try {
            gastoFinal = gastoFinalRepository.save(gastoFinal);
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        return modelMapper.map(obtenerGastoFinalEntity(gastoFinal.getId()), GastoFinalDto.class);
    }

    @Override
    public GastoFinalDto obtenerGastoFinalPorId(Long gastoFinalId) throws AppException {
        GastoFinal gastoFinal = gastoFinalRepository.findById(gastoFinalId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","GASTOFINAL-NOENCONTRADO-404"));
        return modelMapper.map(obtenerGastoFinalEntity(gastoFinal.getId()), GastoFinalDto.class);
    }

    @Override
    public List<GastoFinalDto> obtenerGastosFinales() throws AppException {
        List<GastoFinal> gastosFinales = gastoFinalRepository.findAll();
        return gastosFinales.stream().map(product -> modelMapper.map(product, GastoFinalDto.class)).collect(Collectors.toList());
    }

    private GastoFinal obtenerGastoFinalEntity(Long gastoFinalId) throws AppException {
        return gastoFinalRepository.findById(gastoFinalId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","GASTOFINAL-NOENCONTRADO-404"));
    }
}
