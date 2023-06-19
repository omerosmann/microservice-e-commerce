package microservice.ecommerce.stockservice.api.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import microservice.ecommerce.stockservice.business.abstracts.CategoryService;
import microservice.ecommerce.stockservice.business.dto.requests.creates.CreateCategoryRequest;
import microservice.ecommerce.stockservice.business.dto.requests.updates.UpdateCategoryRequest;
import microservice.ecommerce.stockservice.business.dto.responses.creates.CreateCategoryResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetAllCategoriesResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetCategoryResponse;
import microservice.ecommerce.stockservice.business.dto.responses.updates.UpdateCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryService service;

    @GetMapping
    public List<GetAllCategoriesResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable UUID id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse add(@Valid @RequestBody CreateCategoryRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateCategoryResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCategoryRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    { service.delete(id); }
}