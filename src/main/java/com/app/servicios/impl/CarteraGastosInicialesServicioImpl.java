package com.app.servicios.impl;

import com.app.dto.CarteraGastoInicialDto;
import com.app.dto.CrearCarteraGastoInicialDto;
import com.app.entidades.Cartera;
import com.app.entidades.CarteraGastoInicial;
import com.app.entidades.Formato;
import com.app.entidades.GastoInicial;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.CarteraGastosInicialesRepository;
import com.app.repositorios.CarteraRepository;
import com.app.repositorios.FormatoRepository;
import com.app.repositorios.GastoInicialRepository;
import com.app.servicios.CarteraGastosInicialesServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteraGastosInicialesServicioImpl implements CarteraGastosInicialesServicio {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CarteraGastosInicialesRepository carteraGastosInicialesRepository;
    @Autowired
    private CarteraRepository carteraRepository;
    @Autowired
    private GastoInicialRepository gastoInicialRepository;
    @Autowired
    private FormatoRepository formatoRepository;

    @Transactional
    @Override
    public CarteraGastoInicialDto crearCarteraGastoInicial(CrearCarteraGastoInicialDto crearCarteraGastoInicialDto) throws AppException {
        Cartera cartera = carteraRepository.findById(crearCarteraGastoInicialDto.getCarteraId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-NOENCONTRADO-404"));
        GastoInicial gastoInicial = gastoInicialRepository.findById(crearCarteraGastoInicialDto.getGastoInicialId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","GASTOINICIAL-NOENCONTRADO-404"));

        CarteraGastoInicial carteraGastoInicial = new CarteraGastoInicial();
        carteraGastoInicial.setMGastoInicial(crearCarteraGastoInicialDto.getMGastoInicial());
        carteraGastoInicial.setCartera(cartera);
        carteraGastoInicial.setGastoinicial(gastoInicial);

        try {
            carteraGastoInicial = carteraGastosInicialesRepository.save(carteraGastoInicial);
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        return modelMapper.map(obtenerCarteraGastoInicialEntity(carteraGastoInicial.getId()), CarteraGastoInicialDto.class);
    }

    @Override
    public CarteraGastoInicialDto obtenerPorCarteraYGastoInicial(Long carteraId, Long gastoInicialId) throws AppException {
        CarteraGastoInicial carteraGastoInicial = carteraGastosInicialesRepository.findByCarteraIdAndGastoinicialId(carteraId, gastoInicialId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOINICIAL-NOENCONTRADO-404"));
        return modelMapper.map(obtenerCarteraGastoInicialEntity(carteraGastoInicial.getId()), CarteraGastoInicialDto.class);
    }

    @Override
    public List<CarteraGastoInicialDto> obtenerGastosInicialesPorCartera(Long carteraId) throws AppException {
        List<CarteraGastoInicial> carteraGastosFinales = carteraGastosInicialesRepository.getAllByCarteraId(carteraId);
        return carteraGastosFinales.stream().map(product -> modelMapper.map(product, CarteraGastoInicialDto.class)).collect(Collectors.toList());
    }

    @Override
    public void actualizarMonto(Long carteraId, Long gastoInicialId, Double MGastoInicial) throws AppException {
        CarteraGastoInicial carteraGastoInicial = carteraGastosInicialesRepository.findByCarteraIdAndGastoinicialId(carteraId, gastoInicialId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOINICIAL-NOENCONTRADO-404"));

        carteraGastoInicial.setMGastoInicial(MGastoInicial);

        try {
            carteraGastosInicialesRepository.save(carteraGastoInicial);
        } catch (Exception e) {
            throw new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOINICIAL-NOENCONTRADO-404");
        }
    }

    @Override
    public void actualizarFormato(Long carteraId, Long gastoInicialId, Long formatoId) throws AppException {
        CarteraGastoInicial carteraGastoInicial = carteraGastosInicialesRepository.findByCarteraIdAndGastoinicialId(carteraId, gastoInicialId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOINICIAL-NOENCONTRADO-404"));

        Formato formato = formatoRepository.findById(formatoId)
                        .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","FORMATO-NOENCONTRADO-404"));
        carteraGastoInicial.setFormato(formato);

        try {
            carteraGastosInicialesRepository.save(carteraGastoInicial);
        } catch (Exception e) {
            throw new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOINICIAL-NOENCONTRADO-404");
        }
    }

    @Override
    public void eliminarCarteraGastoInicial(Long carteraId, Long gastoInicialId) throws AppException {
        CarteraGastoInicial carteraGastoInicial = carteraGastosInicialesRepository.findByCarteraIdAndGastoinicialId(carteraId, gastoInicialId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOINICIAL-NOENCONTRADO-404"));

        try {
            carteraGastosInicialesRepository.deleteById(carteraGastoInicial.getId());
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR", "SERVIDOR_INTERNO_ERROR");
        }
    }

    private CarteraGastoInicial obtenerCarteraGastoInicialEntity(Long id) throws AppException {
        return carteraGastosInicialesRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOINICIAL-NOENCONTRADO-404"));
    }
}
