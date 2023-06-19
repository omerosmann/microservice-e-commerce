package microservice.ecommerce.saleservice.api.clients.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;

@Slf4j
@Component
public class PaymentClientFallback implements PaymentClient{
    @Override
    public ClientResponse paymentValidation(CreateSalePaymentRequest request) {
        log.info("PAYMENT SERVICE IS DOWN!");
        throw new RuntimeException("PAYMENT SERVICE IS DOWN!");
    }
}