package com.caixa.cardguard.autorizador.dto;

import java.math.BigDecimal;

public record CompraDTO(String numeroCartao, String senha, BigDecimal valor) {}
