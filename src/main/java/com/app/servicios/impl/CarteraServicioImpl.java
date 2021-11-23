package com.app.servicios.impl;

import com.app.dto.CarteraDto;
import com.app.entidades.*;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.*;
import com.app.servicios.CarteraServicio;
import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteraServicioImpl implements CarteraServicio {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CarteraRepository carteraRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private MonedaRepository monedaRepository;

    @Autowired
    private PeriodoCapitalizacionRepository periodoCapitalizacionRepository;

    @Autowired
    private PlazoRepository plazoRepository;

    @Autowired
    private TasaRepository tasaRepository;

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    ReciboRepository reciboRepository;

    @Transactional
    @Override
    public CarteraDto crearCartera(CarteraDto carteraDto) throws AppException {
        Persona persona = personaRepository.findById(carteraDto.getPersonaId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","PERSONA-NOENCONTRADA-404"));
        Moneda moneda = monedaRepository.findById(carteraDto.getMonedaId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","MONEDA-NOENCONTRADA-404"));
        PeriodoCapitalizacion periodoCapitalizacion = periodoCapitalizacionRepository.findById(carteraDto.getPcId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","PERIODO_CAPITALIZACIÓN-NOENCONTRADO-404"));
        Plazo plazo = plazoRepository.findById(carteraDto.getPlazoId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","PLAZO-NOENCONTRADO-404"));
        Tasa tasa = tasaRepository.findById(carteraDto.getTasaId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","TASA-NOENCONTRADA-404"));
        Dia dia = diaRepository.findById(carteraDto.getDiaId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","DIA-NOENCONTRADO-404"));

        Cartera cartera = new Cartera();
        cartera.setDescuento(carteraDto.getDescuento());
        cartera.setNumPlazo(carteraDto.getPlazo());
        cartera.setNumTaza(carteraDto.getTaza());
        cartera.setPeridoCapitalizacion(carteraDto.getPeridoCapitalizacion());
        cartera.setPersona(persona);
        cartera.setMoneda(moneda);
        cartera.setPeriodocapitalizacion(periodoCapitalizacion);
        cartera.setPlazo(plazo);
        cartera.setTasa(tasa);
        cartera.setDia(dia);

        try {
            cartera = carteraRepository.save(cartera);
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        return modelMapper.map(obtenerCarteraEntity(cartera.getId()), CarteraDto.class);
    }

    @Override
    public List<CarteraDto> verCarteras(Long personaID) throws AppException {
        List<Cartera> carteras = carteraRepository.getAllByPersonaId(personaID);
        return carteras.stream().map(product -> modelMapper.map(product, CarteraDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarteraDto> verCarterasPorMoneda(Long personaID, Long monedaID) throws AppException {
        List<Cartera> carteras = carteraRepository.getAllByPersonaIdAndMonedaId(personaID, monedaID);
        return carteras.stream().map(product -> modelMapper.map(product, CarteraDto.class)).collect(Collectors.toList());
    }

    @Override
    public CarteraDto detallesCartera(Long id) throws AppException {
        Cartera cartera = carteraRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-NOENCONTRADA-404"));
        List<Recibo> recibos = reciboRepository.getAllByCarteraId(id);
        cartera.setNumRecibos(recibos.size());
        // Cálculo TCEA
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, cartera.getDescuento()));
        Float sumaFlujo = 0.0F;
        for (Recibo recibo: recibos){
            transactions.add(new Transaction(cartera.getGastoFTotal() - recibo.getMonto(), recibo.getFecha_pago()));
            sumaFlujo += recibo.getRecibido();
        }
        transactions.set(0, new Transaction(cartera.getRecibidoTotal(), cartera.getDescuento()));
        cartera.setTceaTotal(new Xirr(transactions).xirr());

        return modelMapper.map(obtenerCarteraEntity(cartera.getId()), CarteraDto.class);
    }

    private Cartera obtenerCarteraEntity(Long id) throws AppException{
        return carteraRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-NOENCONTRADA-404"));
    }
}
