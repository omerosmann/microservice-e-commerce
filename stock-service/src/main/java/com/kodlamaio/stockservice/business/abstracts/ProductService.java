package com.kodlamaio.stockservice.business.abstracts;



import com.kodlamaio.commonpackage.dto.ClientResponse;
import com.kodlamaio.stockservice.business.dto.requests.creates.CreateProductRequest;
import com.kodlamaio.stockservice.business.dto.requests.updates.UpdateProductRequest;
import com.kodlamaio.stockservice.business.dto.responses.creates.CreateProductResponse;
import com.kodlamaio.stockservice.business.dto.responses.gets.GetAllProductsResponse;
import com.kodlamaio.commonpackage.dto.GetProductResponse;
import com.kodlamaio.stockservice.business.dto.responses.updates.UpdateProductResponse;
import com.kodlamaio.stockservice.entities.enums.State;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<GetAllProductsResponse> getAll(boolean includeState);
    GetProductResponse getById(UUID id);
    CreateProductResponse add(CreateProductRequest request);
    UpdateProductResponse update (UUID id, UpdateProductRequest request);
    void delete (UUID id);
    GetProductResponse stateByChange(UUID id);
    void changeStateByProductId(State state, UUID id);
    void checkIfProductActive(UUID id);

}