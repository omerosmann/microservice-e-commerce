package microservice.ecommerce.stockservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.ecommerce.commonpackage.dto.GetProductResponse;
import microservice.ecommerce.stockservice.business.abstracts.ProductService;
import microservice.ecommerce.stockservice.business.dto.requests.creates.CreateProductRequest;
import microservice.ecommerce.stockservice.business.dto.requests.updates.UpdateProductRequest;
import microservice.ecommerce.stockservice.business.dto.responses.creates.CreateProductResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetAllProductsResponse;
import microservice.ecommerce.stockservice.business.dto.responses.updates.UpdateProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService service;

    @GetMapping
    public List<GetAllProductsResponse> getAll(@RequestParam(defaultValue = "true") boolean includeState)
    { return service.getAll(includeState); }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable UUID id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@Valid @RequestBody CreateProductRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateProductResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateProductRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    { service.delete(id); }

    @PutMapping("/state/{id}")
    public GetProductResponse stateByChange(@PathVariable UUID id)
    { return service.stateByChange(id); }
}
