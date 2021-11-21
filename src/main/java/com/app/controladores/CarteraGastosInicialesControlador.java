package com.app.controladores;

import com.app.dto.CrearCarteraGastoInicialDto;
import com.app.dto.CarteraGastoInicialDto;
import com.app.excepciones.AppException;
import com.app.responses.AppResponse;
import com.app.servicios.CarteraGastosInicialesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/ureceipt")
public class CarteraGastosInicialesControlador {
    @Autowired
    private CarteraGastosInicialesServicio carteraGastosInicialesServicio;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/cartera/{carteraId}/gastosiniciales")
    public AppResponse<CarteraGastoInicialDto> crearCarteraGastoInicial(@RequestBody CrearCarteraGastoInicialDto crearCarteraGastoInicialDto) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraGastosInicialesServicio.crearCarteraGastoInicial(crearCarteraGastoInicialDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cartera/{carteraId}/gastosiniciales/{gastoinicialId}")
    public AppResponse<CarteraGastoInicialDto> obtenerPorCarteraYGastoInicial(@PathVariable Long carteraId, @PathVariable Long gastoinicialId) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraGastosInicialesServicio.obtenerPorCarteraYGastoInicial(carteraId, gastoinicialId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cartera/{carteraId}/gastosiniciales")
    public AppResponse<List<CarteraGastoInicialDto>> obtenerGastosInicialesPorCartera(@PathVariable Long carteraId) throws AppException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                carteraGastosInicialesServicio.obtenerGastosInicialesPorCartera(carteraId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cartera/{carteraId}/gastosiniciales/{gastoinicialId}/{mgastoinicial}")
    public void actualizarMonto(@PathVariable Long carteraId, @PathVariable Long gastoinicialId, @PathVariable Double mgastoinicial) throws AppException {
        carteraGastosInicialesServicio.actualizarMonto(carteraId, gastoinicialId, mgastoinicial);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cartera/{carteraId}/gastosiniciales/{gastoinicialId}/{formatoId}")
    public void actualizarFormato(@PathVariable Long carteraId, @PathVariable Long gastoinicialId, @PathVariable Long formatoId) throws AppException {
        carteraGastosInicialesServicio.actualizarFormato(carteraId, gastoinicialId, formatoId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/cartera/{carteraId}/gastosiniciales/{gastoinicialId}")
    public void eliminarCarteraGastoInicial(@PathVariable Long carteraId, @PathVariable Long gastoinicialId) throws AppException {
        carteraGastosInicialesServicio.eliminarCarteraGastoInicial(carteraId, gastoinicialId);
    }
}