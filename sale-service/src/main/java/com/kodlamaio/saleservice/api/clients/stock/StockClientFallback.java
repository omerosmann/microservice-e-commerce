package com.kodlamaio.saleservice.api.clients.stock;

import com.kodlamaio.commonpackage.dto.ClientResponse;
import com.kodlamaio.commonpackage.dto.GetProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class StockClientFallback implements StockClient{

    @Override
    public ClientResponse checkIfProductActive(UUID productId) {
        log.info("STOCK SERVICE IS DOWN!");
        throw new RuntimeException("STOCK SERVICE IS DOWN!");
    }
}
