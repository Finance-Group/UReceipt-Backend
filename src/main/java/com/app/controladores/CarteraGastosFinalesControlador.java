package com.app.controladores;

import com.app.dto.CarteraGastoFinalDto;
import com.app.dto.CrearCarteraGastoFinalDto;
import com.app.excepciones.AppException;
import com.app.responses.AppResponse;
import com.app.servicios.CarteraGastosFinalesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ureceipt")
public class CarteraGastosFinalesControlador {
    @Autowired
    private CarteraGastosFinalesServicio carteraGastosFinalesServicio;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/cartera/{carteraId}/gastosfinales")
    public AppResponse<CarteraGastoFinalDto> crearCarteraGastoFinal(@RequestBody CrearCarteraGastoFinalDto crearCarteraGastoFinalDto) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraGastosFinalesServicio.crearCarteraGastoFinal(crearCarteraGastoFinalDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cartera/{carteraId}/gastosfinales/{gastofinalId}")
    public AppResponse<CarteraGastoFinalDto> obtenerPorCarteraYGastoFinal(@PathVariable Long carteraId, @PathVariable Long gastofinalId) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraGastosFinalesServicio.obtenerPorCarteraYGastoFinal(carteraId, gastofinalId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cartera/{carteraId}/gastosfinales")
    public AppResponse<List<CarteraGastoFinalDto>> obtenerGastosFinalesPorCartera(@PathVariable Long carteraId) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraGastosFinalesServicio.obtenerGastosFinalesPorCartera(carteraId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cartera/{carteraId}/gastosfinales/{gastofinalId}/{mgastofinal}")
    public void actualizarMonto(@PathVariable Long carteraId, @PathVariable Long gastofinalId, @PathVariable Double mgastofinal) throws AppException {
        carteraGastosFinalesServicio.actualizarMonto(carteraId, gastofinalId, mgastofinal);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cartera/{carteraId}/gastosfinales/{gastofinalId}/{formatoId}")
    public void actualizarFormato(@PathVariable Long carteraId, @PathVariable Long gastofinalId, @PathVariable Long formatoId) throws AppException {
        carteraGastosFinalesServicio.actualizarFormato(carteraId, gastofinalId, formatoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/cartera/{carteraId}/gastosfinales/{gastofinalId}")
    public void eliminarCarteraGastoFinal(@PathVariable Long carteraId, @PathVariable Long gastofinalId) throws AppException {
        carteraGastosFinalesServicio.eliminarCarteraGastoFinal(carteraId, gastofinalId);
    }
}