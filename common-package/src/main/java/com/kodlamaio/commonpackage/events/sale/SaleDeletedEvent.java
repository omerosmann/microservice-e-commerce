package microservice.ecommerce.commonpackage.events.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.commonpackage.events.Event;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDeletedEvent implements Event {
    private UUID productId;
}
