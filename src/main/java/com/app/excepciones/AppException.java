package com.app.excepciones;
import com.app.dto.ErrorDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AppException extends Exception{
    private final String code;
    private final int responseCode;
    private final List<ErrorDto> errorDtoList = new ArrayList<>();

    public AppException(String code, int responseCode, String message) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
    }

    public AppException(String code, int responseCode, String message, List<ErrorDto> errorDtoList) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
        this.errorDtoList.addAll(errorDtoList);
    }
}
