package com.kodlamaio.paymentservice.business.rules;

import com.kodlamaio.commonpackage.dto.CreateSalePaymentRequest;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import com.kodlamaio.paymentservice.business.dto.dto.requests.CreatePaymentRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(UUID id) {
        if (!repository.existsById(id)) throw new BusinessException("PAYMENT_NOT_EXISTS");

    }

    public void checkIfBalanceIsEnough(double price, double balance) {
        if (balance < price) throw new BusinessException("PAYMENT_NOT_ENOUGH_MONEY");

    }

    public void checkIfCardExists(CreatePaymentRequest request) {
        if (repository.existsByCardNumber(request.getCardNumber()))
            throw new BusinessException("CARD_NUMBER_ALREADY_EXISTS");
    }

    public void checkIfPaymentIsValid(CreateSalePaymentRequest request) {
        if (!repository.existsByCardHolderAndCardNumberAndCardExpirationMonthAndCardExpirationYearAndCardCvv(
                request.getCardHolder(),
                request.getCardNumber(),
                request.getCardExpirationMonth(),
                request.getCardExpirationYear(),
                request.getCardCvv()
        )) {
            throw new BusinessException("NOT_VALID_PAYMENT");
        }
    }
}
