package com.app.servicios;

import com.app.dto.CarteraGastoFinalDto;
import com.app.dto.CrearCarteraGastoFinalDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface CarteraGastosFinalesServicio {
    CarteraGastoFinalDto crearCarteraGastoFinal(CrearCarteraGastoFinalDto crearCarteraGastoFinalDto) throws AppException;
    CarteraGastoFinalDto obtenerPorCarteraYGastoFinal(Long carteraId, Long gastoFinalId) throws AppException;
    List<CarteraGastoFinalDto> obtenerGastosFinalesPorCartera(Long carteraId) throws AppException;
    void actualizarMonto(Long carteraId, Long gastoFinalId,Double MGastoFinal) throws AppException;
    void actualizarFormato(Long carteraId, Long gastoFinalId,Long formatoId) throws AppException;
    void eliminarCarteraGastoFinal(Long carteraId, Long gastoFinalId) throws AppException;
}
