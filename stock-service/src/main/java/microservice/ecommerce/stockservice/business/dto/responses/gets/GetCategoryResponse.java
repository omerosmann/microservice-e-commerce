package microservice.ecommerce.stockservice.business.dto.responses.gets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryResponse {
    private UUID id;
    private String name;
}
