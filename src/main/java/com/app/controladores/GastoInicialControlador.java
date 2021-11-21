package com.app.controladores;

import com.app.dto.CrearGastoInicialDto;
import com.app.dto.GastoInicialDto;
import com.app.excepciones.AppException;
import com.app.responses.AppResponse;
import com.app.servicios.GastoInicialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ureceipt")
public class GastoInicialControlador {
    @Autowired
    private GastoInicialServicio gastoInicialServicio;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/gastosiniciales")
    public AppResponse<GastoInicialDto> crearGastoInicial(@RequestBody CrearGastoInicialDto crearGastoInicialDto) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                gastoInicialServicio.crearGastoInicial(crearGastoInicialDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/gastosiniciales/{gastoinicialId}")
    public AppResponse<GastoInicialDto> obtenerGastoInicialPorId(@PathVariable Long gastoinicialId) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                gastoInicialServicio.obtenerGastoInicialPorId(gastoinicialId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/gastosiniciales")
    public AppResponse<List<GastoInicialDto>> obtenerGastosIniciales() throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                gastoInicialServicio.obtenerGastosIniciales());
    }
}