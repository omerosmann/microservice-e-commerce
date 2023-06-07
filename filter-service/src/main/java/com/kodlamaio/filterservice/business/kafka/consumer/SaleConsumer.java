package com.kodlamaio.filterservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.sale.SaleCreatedEvent;
import com.kodlamaio.commonpackage.events.sale.SaleDeletedEvent;

import com.kodlamaio.filterservice.business.abstracts.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleConsumer {
    private final FilterService service;

    @KafkaListener(
            topics  = "sale-created",
            groupId = "filter-sale-create "
    )
    public void consume(SaleCreatedEvent event) {
        var filter = service.getByProductId(event.getProductId());
        filter.setState("Passive");
        service.add(filter);
        log.info("Sale created event consumed {}", event);
    }

    @KafkaListener(
            topics  = "sale-deleted",
            groupId = "stock-sale-delete "
    )
    public void consume(SaleDeletedEvent event) {
        var filter = service.getByProductId(event.getProductId());
        filter.setState("Active");
        service.add(filter);
        log.info("Sale created event consumed {}", event);
    }
}