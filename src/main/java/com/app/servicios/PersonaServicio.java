package com.app.servicios;

import com.app.dto.PersonaDto;
import com.app.excepciones.AppException;

public interface PersonaServicio {
    PersonaDto crearUsuario(PersonaDto personaDto) throws AppException;
    PersonaDto validarUsuario(Long nDocumento, String contraseña) throws AppException;
    void cambiarContraseña(Long nDocumento, String contraseña) throws AppException;
}
