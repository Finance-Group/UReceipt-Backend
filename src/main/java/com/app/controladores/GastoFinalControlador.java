package com.app.controladores;

import com.app.dto.CrearGastoFinalDto;
import com.app.dto.GastoFinalDto;
import com.app.excepciones.AppException;
import com.app.responses.AppResponse;
import com.app.servicios.GastoFinalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ureceipt")
public class GastoFinalControlador {
    @Autowired
    private GastoFinalServicio gastoFinalServicio;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/gastosfinales")
    public AppResponse<GastoFinalDto> crearGastoFinal(@RequestBody CrearGastoFinalDto crearGastoFinalDto) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                gastoFinalServicio.crearGastoFinal(crearGastoFinalDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/gastosfinales/{gastofinalId}")
    public AppResponse<GastoFinalDto> obtenerGastoFinalPorId(@PathVariable Long gastofinalId) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                gastoFinalServicio.obtenerGastoFinalPorId(gastofinalId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/gastosfinales")
    public AppResponse<List<GastoFinalDto>> obtenerGastosFinales() throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                gastoFinalServicio.obtenerGastosFinales());
    }
}