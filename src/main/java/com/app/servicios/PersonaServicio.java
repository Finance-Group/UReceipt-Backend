package com.app.servicios;

import com.app.dto.PersonaDto;
import com.app.excepciones.AppException;

public interface PersonaServicio {
    PersonaDto crearUsuario(PersonaDto personaDto) throws AppException;
    boolean validarUsuario(PersonaDto personaDto) throws AppException;
    void cambiarContraseña(String correo, String contraseña) throws AppException;
}
