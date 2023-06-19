package microservice.ecommerce.saleservice.api.clients.payment;

import io.github.resilience4j.retry.annotation.Retry;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", fallback = PaymentClientFallback.class)
public interface PaymentClient {
    @Retry(name = "paymentValidation")
    @PostMapping(value = "api/payments/payment-validation")
    ClientResponse paymentValidation(@RequestBody CreateSalePaymentRequest request);
}