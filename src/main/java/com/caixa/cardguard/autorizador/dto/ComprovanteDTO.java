package com.caixa.cardguard.autorizador.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ComprovanteDTO(String status, UUID codigoAutorizacao, BigDecimal limiteRestante) {}
