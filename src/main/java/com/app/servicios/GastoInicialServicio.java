package com.app.servicios;

import com.app.dto.CarteraGastoInicialDto;
import com.app.dto.GastoInicialDto;
import com.app.excepciones.AppException;

import java.util.List;

public interface GastoInicialServicio {
    GastoInicialDto crearGastoInicial(GastoInicialDto gastoInicialDto) throws AppException;
    List<CarteraGastoInicialDto> detallesCarteraGastoInicial(Long carteraID) throws AppException;
}
