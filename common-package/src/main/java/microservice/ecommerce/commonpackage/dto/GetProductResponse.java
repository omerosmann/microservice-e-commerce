package microservice.ecommerce.commonpackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {
    private UUID id;
    private UUID categoryId;
    private String categoryName;
    private String name;
    private String state;
    private int quantity;
    private double price;
    private String description;
}

