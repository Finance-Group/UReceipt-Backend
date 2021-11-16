package com.app.servicios;

import com.app.dto.ReciboDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface ReciboServicio {
    ReciboDto crearRecibo(ReciboDto reciboDto) throws AppException;
    ReciboDto detallesRecibo(Long id) throws AppException;
    List<ReciboDto> mostrarRecibos(Long carteraID) throws AppException;
}
