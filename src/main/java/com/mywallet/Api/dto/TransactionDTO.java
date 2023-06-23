package com.mywallet.Api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionDTO(
    @NotNull double price,
    @NotNull int user_id
) {}
