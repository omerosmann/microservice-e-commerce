package microservice.ecommerce.saleservice.api.clients.stock;

import io.github.resilience4j.retry.annotation.Retry;
import microservice.ecommerce.commonpackage.dto.GetProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "stock-service", fallback = StockClientFallback.class)
public interface StockClient {
    @GetMapping(value = "/api/products/{productId}")
    GetProductResponse getByIdForProduct(@PathVariable UUID productId);
}
