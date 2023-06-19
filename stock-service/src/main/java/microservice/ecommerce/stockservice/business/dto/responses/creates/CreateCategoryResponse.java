package microservice.ecommerce.stockservice.business.dto.responses.creates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryResponse {
    private UUID id;
    private String name;
}
