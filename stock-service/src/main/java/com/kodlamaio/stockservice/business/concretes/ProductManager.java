package com.kodlamaio.stockservice.business.concretes;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.stockservice.business.abstracts.ProductService;
import com.kodlamaio.stockservice.business.dto.requests.creates.CreateProductRequest;
import com.kodlamaio.stockservice.business.dto.requests.updates.UpdateProductRequest;
import com.kodlamaio.stockservice.business.dto.responses.creates.CreateProductResponse;
import com.kodlamaio.stockservice.business.dto.responses.gets.GetAllProductsResponse;
import com.kodlamaio.stockservice.business.dto.responses.gets.GetProductResponse;
import com.kodlamaio.stockservice.business.dto.responses.updates.UpdateProductResponse;
import com.kodlamaio.stockservice.business.rules.ProductBusinessRules;
import com.kodlamaio.stockservice.entities.Product;
import com.kodlamaio.stockservice.entities.enums.State;
import com.kodlamaio.stockservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final ModelMapperService mapper;
    private final ProductBusinessRules rules;

    @Override
    public List<GetAllProductsResponse> getAll(boolean includeState) {
        var products = filterProductByState(includeState);
        var responses = products
                .stream()
                .map(product -> mapper.forResponse().map(product, GetAllProductsResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        rules.checkIfProductExists(id);

        var product = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(product, GetProductResponse.class);

        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        rules.checkIfPriceValue(request.getPrice());
        rules.checkIfQuantityValue(request.getQuantity());
        rules.checkIfDescriptionValue(request.getDescription());

        var product = mapper.forRequest().map(request, Product.class);
        product.setId(UUID.randomUUID());
        product.setState(State.Active);
        repository.save(product);
        var response = mapper.forResponse().map(product, CreateProductResponse.class);

        return response;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        rules.checkIfProductExists(id);
        rules.checkIfPriceValue(request.getPrice());
        rules.checkIfQuantityValue(request.getQuantity());
        rules.checkIfDescriptionValue(request.getDescription());

        var product = mapper.forRequest().map(request, Product.class);
        product.setId(id);
        repository.save(product);
        var response = mapper.forResponse().map(product, UpdateProductResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfProductExists(id);

        repository.deleteById(id);

    }

    @Override
    public GetProductResponse stateByChange(UUID id) {
        rules.checkIfProductExists(id);

        Product product = repository.findById(id).orElseThrow();
        product.setId(id);
        stateChange(product);
        repository.save(product);
        GetProductResponse response = mapper.forResponse().map(product, GetProductResponse.class);

        return response;
    }

    @Override
    public void changeStateByProductId(State state, UUID id)
    { repository.changeStateByProductId(state, id); }

    private void stateChange(Product product) {
        if (product.getState().equals(State.Active)) { product.setState(State.Passive); }
        else { product.setState(State.Active); }
    }

    private List<Product> filterProductByState(boolean includeState) {
        if (includeState) { return repository.findAll(); }

        return repository.findAllByStateIsNot(State.Passive);
    }

}