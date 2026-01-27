package com.caixa.cardguard;

import com.caixa.cardguard.cartoes.Cartao;
import com.caixa.cardguard.cartoes.CartaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(CartaoRepository repository) {
        return args -> {
            // Criar um cartão com R$ 1000,00 de limite
            repository.save(new Cartao("1234-5678", "1234", new BigDecimal("1000.00")));
            System.out.println("--- CARTÃO DE TESTE CRIADO ---");
            System.out.println("Número: 1234-5678 | Senha: 1234 | Limite: 1000.00");
        };
    }
}
