package br.com.simplifiedpicpay.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDtoRequest (BigDecimal amount, UUID payerId, UUID receiverId) {
}
