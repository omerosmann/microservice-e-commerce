package com.kodlamaio.saleservice.api.clients.stock;


import com.kodlamaio.commonpackage.dto.GetProductResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "stock-service", fallback = StockClientFallback.class)
public interface StockClient {

    @Retry(name = "stock-retry")
    @GetMapping(value = "/api/products/check-product-active{productId}")
    GetProductResponse checkIfProductActive(@PathVariable UUID productId);
}
