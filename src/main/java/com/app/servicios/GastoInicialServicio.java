package com.app.servicios;

import com.app.dto.CrearGastoInicialDto;
import com.app.dto.GastoInicialDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface GastoInicialServicio {
    GastoInicialDto crearGastoInicial(CrearGastoInicialDto crearGastoInicialDto) throws AppException;
    GastoInicialDto obtenerGastoInicialPorId(Long gastoInicialId) throws AppException;
    List<GastoInicialDto> obtenerGastosIniciales() throws AppException;
}
