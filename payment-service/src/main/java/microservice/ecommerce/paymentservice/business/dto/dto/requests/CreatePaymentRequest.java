package microservice.ecommerce.paymentservice.business.dto.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {
    @NotBlank
    @Length(min = 5)
    private String cardHolder;

    @NotBlank(message = "Card number cannot be left blank.")
    @Length(min = 16, max = 16, message = "The card number must consist of 16 digits.")
    private String cardNumber;

    @Max(value = 12)
    @Min(value = 1)
    private int cardExpirationMonth;

    @Min(value = 2023)
    private int cardExpirationYear;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 3)
    private String cardCvv;

    @NotNull
    @Min(value = 1)
    private double balance;
}

