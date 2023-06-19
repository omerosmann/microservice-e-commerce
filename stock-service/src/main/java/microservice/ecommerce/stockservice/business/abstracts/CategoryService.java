package microservice.ecommerce.stockservice.business.abstracts;

import microservice.ecommerce.stockservice.business.dto.requests.creates.CreateCategoryRequest;
import microservice.ecommerce.stockservice.business.dto.requests.updates.UpdateCategoryRequest;
import microservice.ecommerce.stockservice.business.dto.responses.creates.CreateCategoryResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetAllCategoriesResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetCategoryResponse;
import microservice.ecommerce.stockservice.business.dto.responses.updates.UpdateCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<GetAllCategoriesResponse> getAll();
    GetCategoryResponse getById(UUID id);
    CreateCategoryResponse add(CreateCategoryRequest request);
    UpdateCategoryResponse update (UUID id, UpdateCategoryRequest request);
    void delete (UUID id);
}
