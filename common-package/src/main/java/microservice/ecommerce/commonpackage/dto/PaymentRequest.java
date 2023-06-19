package microservice.ecommerce.commonpackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String cardHolder;
    private String cardNumber;
    private int cardExpirationMonth;
    private int cardExpirationYear;
    private String cardCvv;
}