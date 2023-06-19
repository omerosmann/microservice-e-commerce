package microservice.ecommerce.paymentservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import microservice.ecommerce.paymentservice.business.abstracts.PaymentService;
import microservice.ecommerce.paymentservice.business.dto.dto.requests.CreatePaymentRequest;
import microservice.ecommerce.paymentservice.business.dto.dto.requests.UpdatePaymentRequest;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.CreatePaymentResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.GetAllPaymentsResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.GetPaymentResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.UpdatePaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/payments")
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    List<GetAllPaymentsResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    GetPaymentResponse getById(@PathVariable UUID id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    UpdatePaymentResponse update(@PathVariable UUID id, @Valid @RequestBody UpdatePaymentRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id)
    { service.delete(id); }

    @PostMapping("/payment-validation")
    ClientResponse processRentalPayment(@RequestBody CreateSalePaymentRequest request)
    { return service.processSalePayment(request); }
}