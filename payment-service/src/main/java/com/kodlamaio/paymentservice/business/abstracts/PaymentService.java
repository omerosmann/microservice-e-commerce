package com.kodlamaio.paymentservice.business.abstracts;

import com.kodlamaio.commonpackage.dto.ClientResponse;
import com.kodlamaio.commonpackage.dto.CreateSalePaymentRequest;
import com.kodlamaio.paymentservice.business.dto.dto.requests.UpdatePaymentRequest;
import com.kodlamaio.paymentservice.business.dto.dto.responses.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.dto.dto.responses.GetAllPaymentsResponse;
import com.kodlamaio.paymentservice.business.dto.dto.responses.GetPaymentResponse;
import com.kodlamaio.paymentservice.business.dto.dto.responses.UpdatePaymentResponse;
import com.kodlamaio.paymentservice.business.dto.dto.requests.CreatePaymentRequest;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
        List<GetAllPaymentsResponse> getAll();
        GetPaymentResponse getById(UUID id);
        CreatePaymentResponse add(CreatePaymentRequest request);
        UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);
        void delete(UUID id);
        ClientResponse processSalePayment(CreateSalePaymentRequest request);
    }