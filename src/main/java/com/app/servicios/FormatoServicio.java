package com.app.servicios;

import com.app.dto.CrearFormatoDto;
import com.app.dto.FormatoDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface FormatoServicio {
    FormatoDto crearFormato(CrearFormatoDto crearFormatoDto) throws AppException;
    FormatoDto obtenerFormatoPorId(Long formatoId) throws AppException;
    List<FormatoDto> obtenerFormatos() throws AppException;
}
