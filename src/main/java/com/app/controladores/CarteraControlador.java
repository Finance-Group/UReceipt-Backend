package com.app.controladores;

import com.app.dto.CarteraDto;
import com.app.excepciones.AppException;
import com.app.responses.AppResponse;
import com.app.servicios.CarteraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ureceipt")
public class CarteraControlador {
    @Autowired
    private CarteraServicio carteraServicio;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/carteras")
    public AppResponse<CarteraDto> crearCartera(@RequestBody CarteraDto carteraDto) throws AppException{
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraServicio.crearCartera(carteraDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/personas/{personaId}/carteras")
    public AppResponse<List<CarteraDto>> verCarteras(@PathVariable Long personaId) throws AppException{
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraServicio.verCarteras(personaId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/personas/{personaId}/carteras/{monedaId}")
    public AppResponse<List<CarteraDto>> verCarterasPorMoneda(@PathVariable Long personaId, @PathVariable Long monedaID) throws AppException{
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraServicio.verCarterasPorMoneda(personaId, monedaID));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/carteras/{id}")
    public AppResponse<CarteraDto> detallesCartera(@PathVariable Long id) throws AppException{
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraServicio.detallesCartera(id));
    }
}
