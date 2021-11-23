package com.app.servicios;

import com.app.dto.CarteraDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface CarteraServicio {
    CarteraDto crearCartera(CarteraDto carteraDto) throws AppException;
    List<CarteraDto> verCarteras(Long personaID) throws AppException;
    List<CarteraDto> verCarterasPorMoneda(Long personaID, Long monedaID) throws AppException;
    CarteraDto detallesCartera(Long id) throws AppException;
}
