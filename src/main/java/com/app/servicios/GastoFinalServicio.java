package com.app.servicios;

import com.app.dto.CarteraGastoFinalDto;
import com.app.dto.GastoFinalDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface GastoFinalServicio {
    GastoFinalDto crearGastoFinal(GastoFinalDto gastoFinalDto) throws AppException;
    List<CarteraGastoFinalDto> detallesCarteraGastoFinal(Long carteraID) throws AppException;
}
