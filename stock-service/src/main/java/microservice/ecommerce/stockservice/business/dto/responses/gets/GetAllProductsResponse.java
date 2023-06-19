package microservice.ecommerce.stockservice.business.dto.responses.gets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.stockservice.entities.enums.State;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsResponse {
    private UUID id;
    private UUID categoryId;
    private String categoryName;
    private String name;
    private State state;
    private int quantity;
    private double price;
    private String description;
}
