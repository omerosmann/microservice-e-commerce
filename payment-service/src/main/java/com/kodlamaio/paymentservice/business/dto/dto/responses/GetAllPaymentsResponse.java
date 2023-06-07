package com.kodlamaio.paymentservice.business.dto.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPaymentsResponse {
    private UUID id;
    private String cardHolder;
    private String cardNumber;
    private int cardExpirationMonth;
    private int cardExpirationYear;
    private String cardCvv;
    private double balance;
}