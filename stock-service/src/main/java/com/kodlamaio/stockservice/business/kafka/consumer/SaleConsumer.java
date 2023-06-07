package microservice.ecommerce.stockservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.ecommerce.commonpackage.events.sale.SaleCreatedEvent;
import microservice.ecommerce.commonpackage.events.sale.SaleDeletedEvent;
import microservice.ecommerce.stockservice.business.abstracts.ProductService;
import microservice.ecommerce.stockservice.entities.enums.State;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleConsumer {
    private final ProductService service;

    @KafkaListener(
            topics  = "sale-created",
            groupId = "stock-sale-create "
    )
    public void consume(SaleCreatedEvent event) {
        service.changeStateByProductId(State.Active, event.getProductId());
        log.info("Rental created event consumed {}", event);
    }

    @KafkaListener(
            topics  = "sale-deleted",
            groupId = "stock-sale-delete "
    )
    public void consume(SaleDeletedEvent event) {
        service.changeStateByProductId(State.Passive, event.getProductId());
        log.info("Rental created event consumed {}", event);
    }
}