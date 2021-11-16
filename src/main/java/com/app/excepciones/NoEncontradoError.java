package com.app.excepciones;
import com.app.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NoEncontradoError extends AppException{
    public NoEncontradoError(String code, String message) {
        super(code, HttpStatus.NOT_FOUND.value(), message);
    }

    public NoEncontradoError(String code, String message, ErrorDto data) {
        super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
    }
}
