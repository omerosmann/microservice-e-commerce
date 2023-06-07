package com.kodlamaio.filterservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.stock.CategoryDeletedEvent;
import com.kodlamaio.commonpackage.events.stock.ProductCreatedEvent;
import com.kodlamaio.commonpackage.events.stock.ProductDeletedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockConsumer {
    private final FilterService filterService;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics  = "product-created",
            groupId = "product-create"
    )
    public void consume(ProductCreatedEvent event){
        Filter filter = mapper.forRequest().map(event, Filter.class);

        filterService.add(filter);

        log.info("Product created event consume {}", event);
    }

    @KafkaListener(
            topics  = "product-deleted",
            groupId = "product-delete"
    )
    public void consume(ProductDeletedEvent event){
        Filter filter = mapper.forRequest().map(event, Filter.class);

        filterService.deleteByProductId(event.getProductId());

        log.info("Product deleted event consume {}", event);
    }

    @KafkaListener(
            topics  = "category-deleted",
            groupId = "category-delete"
    )
    public void consume(CategoryDeletedEvent event){
        Filter filter = mapper.forRequest().map(event, Filter.class);

        filterService.deleteAllByCategoryId(event.getCategoryId());

        log.info("Category deleted event consume {}", event);
    }
}
