package com.app.servicios.impl;

import com.app.dto.ReciboDto;
import com.app.entidades.Cartera;
import com.app.entidades.Recibo;
import com.app.excepciones.AppException;
import com.app.excepciones.NoEncontradoError;
import com.app.excepciones.ServidorInternoError;
import com.app.repositorios.CarteraRepository;
import com.app.repositorios.ReciboRepository;
import com.app.servicios.ReciboServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ReciboServicioImpl implements ReciboServicio {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ReciboRepository reciboRepository;

    @Autowired
    private CarteraRepository carteraRepository;

    @Transactional
    @Override
    public ReciboDto crearRecibo(ReciboDto reciboDto) throws AppException {
        Cartera cartera = carteraRepository.findById(reciboDto.getCarteraId())
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","CARTERA-NOENCONTRADA-404"));

        Recibo recibo = new Recibo();
        recibo.setFecha_emision(reciboDto.getFecha_emision());
        recibo.setFecha_pago(reciboDto.getFecha_pago());
        recibo.setMonto(reciboDto.getMonto());
        recibo.setRetencion(reciboDto.getRetencion());

        // RESTA DÍAS
        long tempDias = reciboDto.getFecha_pago().getTime() - reciboDto.getFecha_emision().getTime();
        TimeUnit timeUnit = TimeUnit.DAYS;
        Integer dias = Math.toIntExact(timeUnit.convert(tempDias, TimeUnit.MILLISECONDS));
        recibo.setDias(dias);
        // Calcular TE %
        Long m = cartera.getDia().getNumero() / cartera.getPeridoCapitalizacion();
        if (cartera.getTasa().getNombre() == "NOMINAL") {
            Float base = 1 + (cartera.getNumTaza() / m);
            recibo.setTasa_e((float) Math.pow(base, recibo.getDias()) - 1);
        } else if (cartera.getTasa().getNombre() == "EFECTIVA"){
            Long exponente = recibo.getDias() / m;
            recibo.setTasa_e((float) (Math.pow(1 + cartera.getNumTaza(), exponente) - 1));
        } else throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        // Calcular D %
        recibo.setTasa_descuento(recibo.getTasa_e() / (1 + recibo.getTasa_e()));
        // Calcular D Monto
        recibo.setMonto_descuento(recibo.getMonto() * recibo.getTasa_descuento());
        // Calcular Monto neto
        recibo.setNeto(recibo.getMonto() - recibo.getMonto_descuento());
        // Calcular Monto recibido
        recibo.setRecibido((float) (recibo.getNeto() + cartera.getGastoITotal()));
        // Calcular Monto entregado
        recibo.setEntregado((float) (recibo.getNeto() - cartera.getGastoFTotal()));
        // Calcular TCEA
        Float baseTCEA = recibo.getEntregado() / recibo.getRecibido();
        Long exponenteTCEA = m / recibo.getDias();
        recibo.setTcea((float) (Math.pow(baseTCEA, exponenteTCEA) - 1));

        try {
            recibo = reciboRepository.save(recibo);
        } catch (Exception e) {
            throw new ServidorInternoError("SERVIDOR_INTERNO_ERROR","SERVIDOR_INTERNO_ERROR");
        }
        // return modelMapper.map(obtenerCarteraEntity(cartera.getId()), CarteraDto.class);
        return modelMapper.map(obtenerReciboEntity(recibo.getId()), ReciboDto.class);
    }

    @Override
    public ReciboDto detallesRecibo(Long id) throws AppException {
        Recibo recibo = reciboRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","RECIBO-NOENCONTRADO-404"));
        return modelMapper.map(obtenerReciboEntity(recibo.getId()), ReciboDto.class);
    }

    @Override
    public List<ReciboDto> mostrarRecibos(Long carteraID) throws AppException {
        List<Recibo> recibos = reciboRepository.getAllByCarteraId(carteraID);
        return recibos.stream().map(product -> modelMapper.map(product, ReciboDto.class)).collect(Collectors.toList());
    }

    private Recibo obtenerReciboEntity(Long id) throws AppException{
        return reciboRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoError("NO ENCONTRADO-404","RECIBO-NOENCONTRADO-404"));
    }
}
