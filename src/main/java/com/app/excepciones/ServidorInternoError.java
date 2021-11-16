package com.app.excepciones;
import com.app.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class ServidorInternoError extends AppException{
    public ServidorInternoError(String code, String message){
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public ServidorInternoError(String code, String message, ErrorDto data){
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
    }
}
