package com.kodlamaio.stockservice.api.controllers;

import com.kodlamaio.commonpackage.dto.ClientResponse;
import com.kodlamaio.stockservice.business.abstracts.ProductService;
import com.kodlamaio.stockservice.business.dto.requests.creates.CreateProductRequest;
import com.kodlamaio.stockservice.business.dto.requests.updates.UpdateProductRequest;
import com.kodlamaio.stockservice.business.dto.responses.creates.CreateProductResponse;
import com.kodlamaio.stockservice.business.dto.responses.gets.GetAllProductsResponse;
import com.kodlamaio.commonpackage.dto.GetProductResponse;
import com.kodlamaio.stockservice.business.dto.responses.updates.UpdateProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @GetMapping(value = "/check-product-active{productId}")
    public void checkIfProductActive(@PathVariable UUID id){
        service.checkIfProductActive(id);
    }
}
