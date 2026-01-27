package com.caixa.cardguard.transacoes;

import com.caixa.cardguard.cartoes.Cartao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor
public class Transacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;
    
    private BigDecimal valor;
    private LocalDateTime dataHora;

    public Transacao(Cartao cartao, BigDecimal valor) {
        this.cartao = cartao;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }
}