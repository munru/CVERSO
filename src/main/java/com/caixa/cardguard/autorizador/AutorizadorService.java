package com.caixa.cardguard.autorizador;

import com.caixa.cardguard.autorizador.dto.*;
import com.caixa.cardguard.cartoes.*;
import com.caixa.cardguard.transacoes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class AutorizadorService {

    @Autowired private CartaoRepository cartaoRepo;
    @Autowired private TransacaoRepository transacaoRepo;

    @Transactional
    public ComprovanteDTO processarCompra(CompraDTO compra) {
        Cartao cartao = cartaoRepo.findByNumero(compra.numeroCartao())
                .orElseThrow(() -> new RuntimeException("Cartão Inexistente"));

        
        if (!cartao.isAtivo()) throw new RuntimeException("Cartão Bloqueado");
        if (!cartao.getSenha().equals(compra.senha())) throw new RuntimeException("Senha Incorreta");

       
        cartao.debitar(compra.valor()); 
        cartaoRepo.save(cartao);

       
        Transacao transacao = new Transacao(cartao, compra.valor());
        transacaoRepo.save(transacao);

        return new ComprovanteDTO("APROVADA", UUID.randomUUID(), cartao.getLimiteDisponivel());
    }
}
