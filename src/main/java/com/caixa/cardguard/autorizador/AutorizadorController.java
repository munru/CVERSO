package com.caixa.cardguard.autorizador;

import com.caixa.cardguard.autorizador.dto.CompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compras")
public class AutorizadorController {

    @Autowired private AutorizadorService service;

    @PostMapping
    public ResponseEntity<?> comprar(@RequestBody CompraDTO dados) {
        try {
            return ResponseEntity.ok(service.processarCompra(dados));
        } catch (RuntimeException e) {
            
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }
}
