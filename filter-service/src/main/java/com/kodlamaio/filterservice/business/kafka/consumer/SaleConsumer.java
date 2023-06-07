package com.kodlamaio.stockservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.sale.SaleCreatedEvent;
import com.kodlamaio.commonpackage.events.sale.SaleDeletedEvent;
import com.kodlamaio.stockservice.business.abstracts.ProductService;
import com.kodlamaio.stockservice.entities.enums.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        service.changeStateByProductId(State.Passive, event.getProductId());
        log.info("Sale created event consumed {}", event);
    }

    @KafkaListener(
            topics  = "sale-deleted",
            groupId = "stock-sale-delete "
    )
    public void consume(SaleDeletedEvent event) {
        service.changeStateByProductId(State.Active, event.getProductId());
        log.info("Sale created event consumed {}", event);
    }
}