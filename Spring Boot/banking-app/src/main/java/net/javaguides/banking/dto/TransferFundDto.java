package net.javaguides.banking.dto;

import java.math.BigDecimal;

public record TransferFundDto(Long fromAccountId, Long toAccountId, double amount) {

}
