package com.app.controladores;

import com.app.dto.CarteraDto;
import com.app.dto.ReciboDto;
import com.app.excepciones.AppException;
import com.app.responses.AppResponse;
import com.app.servicios.ReciboServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ureceipt")
public class ReciboControlador {
    @Autowired
    private ReciboServicio reciboServicio;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/recibos")
    public AppResponse<ReciboDto> crearRecibo(@RequestBody ReciboDto reciboDto) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                reciboServicio.crearRecibo(reciboDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/carteras/{carteraid}/recibos")
    public AppResponse<List<ReciboDto>> mostrarRecibos(@PathVariable Long carteraid) throws AppException{
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                reciboServicio.mostrarRecibos(carteraid));
    }
}
