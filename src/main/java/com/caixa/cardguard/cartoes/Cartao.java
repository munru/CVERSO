package com.caixa.cardguard.cartoes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
public class Cartao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String senha;
    private BigDecimal limiteDisponivel;
    private boolean ativo;

    public Cartao(String numero, String senha, BigDecimal limiteDisponivel) {
        this.numero = numero;
        this.senha = senha;
        this.limiteDisponivel = limiteDisponivel;
        this.ativo = true;
    }

   
    public void debitar(BigDecimal valor) {
        if (this.limiteDisponivel.compareTo(valor) < 0) {
            throw new RuntimeException("Saldo Insuficiente");
        }
        this.limiteDisponivel = this.limiteDisponivel.subtract(valor);
    }
}