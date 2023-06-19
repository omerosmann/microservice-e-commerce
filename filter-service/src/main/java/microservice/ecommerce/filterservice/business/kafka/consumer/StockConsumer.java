package microservice.ecommerce.filterservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.ecommerce.commonpackage.events.stock.ProductCreatedEvent;
import microservice.ecommerce.commonpackage.events.stock.ProductDeletedEvent;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import microservice.ecommerce.filterservice.business.abstracts.FilterService;
import microservice.ecommerce.filterservice.entities.Filter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockConsumer {
    private final FilterService filterService;
    private final ModelMapperService mapperService;

    @KafkaListener(
            topics  = "product-created",
            groupId = "product-create"
    )
    public void consume(ProductCreatedEvent event){
        Filter filter = mapperService.forRequest().map(event, Filter.class);

        filterService.add(filter);

        log.info("Car created event consume {}", event);
    }

    @KafkaListener(
            topics  = "product-deleted",
            groupId = "product-delete"
    )
    public void consume(ProductDeletedEvent event){
        Filter filter = mapperService.forRequest().map(event, Filter.class);

        filterService.deleteByProductId(event.getProductId());

        log.info("Car deleted event consume {}", event);
    }
}
