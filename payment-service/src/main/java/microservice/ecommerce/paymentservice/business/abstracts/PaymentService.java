package microservice.ecommerce.paymentservice.business.abstracts;

import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.requests.CreatePaymentRequest;
import microservice.ecommerce.paymentservice.business.dto.dto.requests.UpdatePaymentRequest;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.CreatePaymentResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.GetAllPaymentsResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.GetPaymentResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.UpdatePaymentResponse;

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