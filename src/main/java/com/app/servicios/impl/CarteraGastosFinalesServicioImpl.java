package com.app.servicios.impl;

import com.app.dto.CarteraGastoFinalDto;
import com.app.dto.CrearCarteraGastoFinalDto;
import com.app.entidades.Cartera;
import com.app.entidades.CarteraGastoFinal;
import com.app.entidades.GastoFinal;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.CarteraGastosFinalesRepository;
import com.app.repositorios.CarteraRepository;
import com.app.repositorios.GastoFinalRepository;
import com.app.servicios.CarteraGastosFinalesServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteraGastosFinalesServicioImpl implements CarteraGastosFinalesServicio {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CarteraGastosFinalesRepository carteraGastosFinalesRepository;
    @Autowired
    private CarteraRepository carteraRepository;
    @Autowired
    private GastoFinalRepository gastoFinalRepository;

    @Transactional
    @Override
    public CarteraGastoFinalDto crearCarteraGastoFinal(CrearCarteraGastoFinalDto crearCarteraGastoFinalDto) throws AppException {
        Cartera cartera = carteraRepository.findById(crearCarteraGastoFinalDto.getCarteraId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-NOENCONTRADO-404"));
        GastoFinal gastoFinal = gastoFinalRepository.findById(crearCarteraGastoFinalDto.getGastoFinalId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","GASTOFINAL-NOENCONTRADO-404"));

        CarteraGastoFinal carteraGastoFinal = new CarteraGastoFinal();
        carteraGastoFinal.setMGastoFinal(crearCarteraGastoFinalDto.getMGastoFinal());
        carteraGastoFinal.setCartera(cartera);
        carteraGastoFinal.setGastofinal(gastoFinal);

        try {
            carteraGastoFinal = carteraGastosFinalesRepository.save(carteraGastoFinal);
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        return modelMapper.map(obtenerCarteraGastoFinalEntity(carteraGastoFinal.getId()), CarteraGastoFinalDto.class);
    }

    @Override
    public CarteraGastoFinalDto obtenerPorCarteraYGastoFinal(Long carteraId, Long gastoFinalId) throws AppException {
        CarteraGastoFinal carteraGastoFinal = carteraGastosFinalesRepository.findByCarteraIdAndGastofinalId(carteraId, gastoFinalId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOFINAL-NOENCONTRADO-404"));
        return modelMapper.map(obtenerCarteraGastoFinalEntity(carteraGastoFinal.getId()), CarteraGastoFinalDto.class);
    }

    @Override
    public List<CarteraGastoFinalDto> obtenerGastosFinalesPorCartera(Long carteraId) throws AppException {
        List<CarteraGastoFinal> carteraGastosFinales = carteraGastosFinalesRepository.getAllByCarteraId(carteraId);
        return carteraGastosFinales.stream().map(product -> modelMapper.map(product, CarteraGastoFinalDto.class)).collect(Collectors.toList());
    }

    @Override
    public void actualizarMonto(Long carteraId, Long gastoFinalId, Double MGastoFinal) throws AppException {
        CarteraGastoFinal carteraGastoFinal = carteraGastosFinalesRepository.findByCarteraIdAndGastofinalId(carteraId, gastoFinalId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOFINAL-NOENCONTRADO-404"));

        carteraGastoFinal.setMGastoFinal(MGastoFinal);

        try {
            carteraGastosFinalesRepository.save(carteraGastoFinal);
        } catch (Exception e) {
            throw new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOFINAL-NOENCONTRADO-404");
        }
    }

    @Override
    public void actualizarFormato(Long carteraId, Long gastoFinalId, Long formatoId) throws AppException {
        CarteraGastoFinal carteraGastoFinal = carteraGastosFinalesRepository.findByCarteraIdAndGastofinalId(carteraId, gastoFinalId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOFINAL-NOENCONTRADO-404"));

        //carteraGastoFinal.setFormato(formatoId);

        try {
            carteraGastosFinalesRepository.save(carteraGastoFinal);
        } catch (Exception e) {
            throw new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOFINAL-NOENCONTRADO-404");
        }
    }

    @Override
    public void eliminarCarteraGastoFinal(Long carteraId, Long gastoFinalId) throws AppException {
        CarteraGastoFinal carteraGastoFinal = carteraGastosFinalesRepository.findByCarteraIdAndGastofinalId(carteraId, gastoFinalId)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOFINAL-NOENCONTRADO-404"));

        try {
            carteraGastosFinalesRepository.deleteById(carteraGastoFinal.getId());
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR", "SERVIDOR_INTERNO_ERROR");
        }
    }

    private CarteraGastoFinal obtenerCarteraGastoFinalEntity(Long id) throws AppException {
        return carteraGastosFinalesRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-GASTOFINAL-NOENCONTRADO-404"));
    }
}
