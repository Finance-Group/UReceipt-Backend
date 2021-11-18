package com.app.servicios;

import com.app.dto.CrearGastoFinalDto;
import com.app.dto.GastoFinalDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface GastoFinalServicio {
    GastoFinalDto crearGastoFinal(CrearGastoFinalDto crearGastoFinalDto) throws AppException;
    GastoFinalDto obtenerGastoFinalPorId(Long gastoFinalId) throws AppException;
    List<GastoFinalDto> obtenerGastosFinales() throws AppException;
}
