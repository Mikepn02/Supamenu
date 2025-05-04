package com.mikepn.supamenubackend.v1.dto.request.transaction;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateTransactionDTO {
    private double amount;
    private String destinationAccountNumber;
    private String sourceAccountNumber;
}
