package com.mywallet.Api.dto;

import jakarta.validation.constraints.NotNull;

public record TransactionDTO(
    @NotNull double price,
    @NotNull int user_id
) {}
