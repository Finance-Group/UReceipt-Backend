package com.app.servicios.impl;

import com.app.dto.CrearGastoInicialDto;
import com.app.dto.GastoFinalDto;
import com.app.dto.GastoInicialDto;
import com.app.entidades.GastoFinal;
import com.app.entidades.GastoInicial;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.GastoInicialRepository;
import com.app.servicios.GastoInicialServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GastoInicialServicioImpl implements GastoInicialServicio {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private GastoInicialRepository gastoInicialRepository;
    
    @Transactional
    @Override
    public GastoInicialDto crearGastoInicial(CrearGastoInicialDto crearGastoInicialDto) throws AppException {
        GastoInicial gastoInicial = new GastoInicial();
        gastoInicial.setNombre(crearGastoInicialDto.getNombre());

        try {
            gastoInicial = gastoInicialRepository.save(gastoInicial);
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        return modelMapper.map(obtenerGastoInicialEntity(gastoInicial.getId()), GastoInicialDto.class);
    }

    @Override
    public GastoInicialDto obtenerGastoInicialPorId(Long gastoInicialId) throws AppException {
        GastoInicial gastoInicial = gastoInicialRepository.findById(gastoInicialId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","GASTOINICIAL-NOENCONTRADO-404"));
        return modelMapper.map(obtenerGastoInicialEntity(gastoInicial.getId()), GastoInicialDto.class);
    }

    @Override
    public List<GastoInicialDto> obtenerGastosIniciales() throws AppException {
        List<GastoInicial> gastosIniciales = gastoInicialRepository.findAll();
        return gastosIniciales.stream().map(product -> modelMapper.map(product, GastoInicialDto.class)).collect(Collectors.toList());
    }

    private GastoInicial obtenerGastoInicialEntity(Long gastoInicialId) throws AppException {
        return gastoInicialRepository.findById(gastoInicialId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","GASTOINICIAL-NOENCONTRADO-404"));
    }
}
