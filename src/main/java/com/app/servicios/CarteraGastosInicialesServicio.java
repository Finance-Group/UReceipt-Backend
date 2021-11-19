package com.app.servicios;

import com.app.dto.CarteraGastoInicialDto;
import com.app.dto.CrearCarteraGastoInicialDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface CarteraGastosInicialesServicio {
    CarteraGastoInicialDto crearCarteraGastoInicial(CrearCarteraGastoInicialDto crearCarteraGastoInicialDto) throws AppException;
    CarteraGastoInicialDto obtenerPorCarteraYGastoInicial(Long carteraId, Long gastoInicialId) throws AppException;
    List<CarteraGastoInicialDto> obtenerGastosInicialesPorCartera(Long carteraId) throws AppException;
    void actualizarMonto(Long carteraId, Long gastoInicialId,Double MGastoInicial) throws AppException;
    void actualizarFormato(Long carteraId, Long gastoInicialId,Long formatoId) throws AppException;
    void eliminarCarteraGastoInicial(Long carteraId, Long gastoInicialId) throws AppException;
}
