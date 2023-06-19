package microservice.ecommerce.stockservice.business.concretes;

import lombok.AllArgsConstructor;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import microservice.ecommerce.stockservice.business.abstracts.CategoryService;
import microservice.ecommerce.stockservice.business.dto.requests.creates.CreateCategoryRequest;
import microservice.ecommerce.stockservice.business.dto.requests.updates.UpdateCategoryRequest;
import microservice.ecommerce.stockservice.business.dto.responses.creates.CreateCategoryResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetAllCategoriesResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetCategoryResponse;
import microservice.ecommerce.stockservice.business.dto.responses.updates.UpdateCategoryResponse;
import microservice.ecommerce.stockservice.business.rules.CategoryBusinessRules;
import microservice.ecommerce.stockservice.entities.Category;
import microservice.ecommerce.stockservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository repository;
    private final ModelMapperService mapper;
    private final CategoryBusinessRules rules;

    @Override
    public List<GetAllCategoriesResponse> getAll() {
        List<Category> categories = repository.findAll();

        List<GetAllCategoriesResponse> responses = categories
                .stream()
                .map(category -> mapper.forResponse().map(category, GetAllCategoriesResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetCategoryResponse getById(UUID id) {
        rules.checkIfCategoryExists(id);

        Category category = repository.findById(id).orElseThrow();

        GetCategoryResponse response = mapper.forResponse().map(category, GetCategoryResponse.class);

        return response;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        rules.CheckIfCategoryNameLength(request.getName());

        Category category = mapper.forRequest().map(request, Category.class);

        category.setId(UUID.randomUUID());
        repository.save(category);

        CreateCategoryResponse response = mapper.forResponse().map(category, CreateCategoryResponse.class);

        return response;
    }

    @Override
    public UpdateCategoryResponse update(UUID id, UpdateCategoryRequest request) {
        rules.checkIfCategoryExists(id);

        Category category = mapper.forRequest().map(request, Category.class);

        category.setId(id);
        repository.save(category);

        UpdateCategoryResponse response = mapper.forResponse().map(category, UpdateCategoryResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCategoryExists(id);

        repository.deleteById(id);
    }
}