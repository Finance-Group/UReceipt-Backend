package com.app.dto;

import com.app.entidades.Cartera;
import com.app.entidades.GastoFinal;
import lombok.Data;

@Data
public class CarteraGastoFinalDto {
    private Long id;
    private Cartera cartera;
    private GastoFinal gastofinal;
    private Double MGastoFinal;
}
