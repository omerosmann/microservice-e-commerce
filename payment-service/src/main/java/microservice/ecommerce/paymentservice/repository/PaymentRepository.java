package microservice.ecommerce.paymentservice.repository;

import microservice.ecommerce.paymentservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Payment findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
    boolean existsByCardHolderAndCardNumberAndCardExpirationMonthAndCardExpirationYearAndCardCvv(
            String cardHolder, String cardNumber, int cardExpirationMonth, int cardExpirationYear, String cardCvv
    );
}