package com.app.controladores;

import com.app.dto.CrearFormatoDto;
import com.app.dto.FormatoDto;
import com.app.excepciones.AppException;
import com.app.responses.AppResponse;
import com.app.servicios.FormatoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ureceipt")
public class FormatoControlador {
    @Autowired
    private FormatoServicio formatoServicio;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/formatos")
    public AppResponse<FormatoDto> crearFormato(@RequestBody CrearFormatoDto crearFormatoDto) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                formatoServicio.crearFormato(crearFormatoDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/formatos/{formatoId}")
    public AppResponse<FormatoDto> obtenerFormatoPorId(@PathVariable Long formatoId) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                formatoServicio.obtenerFormatoPorId(formatoId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/formatos")
    public AppResponse<List<FormatoDto>> obtenerFormatos() throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                formatoServicio.obtenerFormatos());
    }
}