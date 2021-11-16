package com.app.servicios;

import com.app.dto.CarteraDto;
import com.app.excepciones.AppException;

public interface CarteraServicio {
    CarteraDto crearCartera(CarteraDto carteraDto) throws AppException;
    CarteraDto verCarteras(Long personaID) throws AppException;
    CarteraDto verCarterasPorMoneda(Long personaID, Long monedaID) throws AppException;
    CarteraDto detallesCartera(Long id) throws AppException;
}
