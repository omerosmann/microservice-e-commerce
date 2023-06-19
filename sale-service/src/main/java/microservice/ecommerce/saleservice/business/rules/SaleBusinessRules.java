package microservice.ecommerce.saleservice.business.rules;

import lombok.AllArgsConstructor;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import microservice.ecommerce.commonpackage.utils.exceptions.BusinessException;
import microservice.ecommerce.saleservice.api.clients.payment.PaymentClient;
import microservice.ecommerce.saleservice.api.clients.stock.StockClient;
import microservice.ecommerce.saleservice.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleBusinessRules {
    private final StockClient stockClient;
    private final SaleRepository repository;
    private final PaymentClient paymentClient;

    public void checkIfSaleExists(UUID id)
    { if (!repository.existsById(id)) throw new BusinessException("SALE_NOT_EXISTS"); }

    public void checkIfQuantity(int quantity)
    { if (quantity <= 0) throw new BusinessException("PRODUCT_NOT_IN_STOCK"); }

    public void ensurePaymentIsValid(CreateSalePaymentRequest request){
        ClientResponse response = paymentClient.paymentValidation(request);

        if (!response.isSuccess()) throw new BusinessException(response.getMessage());
    }
}