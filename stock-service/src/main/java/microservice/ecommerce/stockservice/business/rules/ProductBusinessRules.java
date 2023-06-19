package microservice.ecommerce.stockservice.business.rules;

import lombok.AllArgsConstructor;
import microservice.ecommerce.stockservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository repository;

    public void checkIfProductExists(UUID id)
    { if (!repository.existsById(id)) throw new RuntimeException("Product not exists!"); }

    public void checkIfPriceValue(double price)
    { if (price <= 0) throw new RuntimeException("Product price must be greater than 0!"); }

    public void checkIfQuantityValue(int quantity)
    { if (quantity < 0 ) throw new RuntimeException("Product quantity cannot be less than 0!"); }

    public void checkIfDescriptionValue (String description)
    { if ((description.length() < 10) || (description.length() >50 ))
        throw new RuntimeException("Product description must contain at 10 characters an at most 50 characters!"); }
}