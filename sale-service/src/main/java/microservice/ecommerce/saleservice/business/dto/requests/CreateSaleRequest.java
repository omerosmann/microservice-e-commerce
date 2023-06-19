package microservice.ecommerce.saleservice.business.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.commonpackage.dto.PaymentRequest;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleRequest {
    private UUID productId;
    private double price;
    private int quantity;
    private PaymentRequest paymentRequest;
}
