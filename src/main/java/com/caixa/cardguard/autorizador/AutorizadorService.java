package com.caixa.cardguard.autorizador;

import com.caixa.cardguard.autorizador.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class AutorizadorService {

    @Transactional
    public ComprovanteDTO processarCompra(CompraDTO compra) {
        return new ComprovanteDTO();
    }
}
