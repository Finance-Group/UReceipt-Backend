package com.app.servicios;

import com.app.dto.ReciboDto;
import com.app.excepciones.AppException;

public interface ReciboServicio {
    ReciboDto crearRecibo(ReciboDto reciboDto) throws AppException;
    ReciboDto detallesRecibo(Long id) throws AppException;
}
