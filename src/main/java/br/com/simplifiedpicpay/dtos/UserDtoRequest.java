package br.com.simplifiedpicpay.dtos;

import br.com.simplifiedpicpay.enums.WalletType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public record UserDtoRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String document,
        @NotBlank
        @Email
        String email,
        String password,
        @NotNull
        BigDecimal balance,
        @NotNull
        WalletType walletType
) {
}
