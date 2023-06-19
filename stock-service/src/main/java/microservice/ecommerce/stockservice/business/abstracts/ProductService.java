package microservice.ecommerce.stockservice.business.abstracts;

import microservice.ecommerce.commonpackage.dto.GetProductResponse;
import microservice.ecommerce.stockservice.business.dto.requests.creates.CreateProductRequest;
import microservice.ecommerce.stockservice.business.dto.requests.updates.UpdateProductRequest;
import microservice.ecommerce.stockservice.business.dto.responses.creates.CreateProductResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetAllProductsResponse;
import microservice.ecommerce.stockservice.business.dto.responses.updates.UpdateProductResponse;
import microservice.ecommerce.stockservice.entities.enums.State;

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
}