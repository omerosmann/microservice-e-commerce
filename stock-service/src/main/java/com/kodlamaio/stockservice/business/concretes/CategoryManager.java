package com.kodlamaio.stockservice.business.concretes;


import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.stockservice.business.abstracts.CategoryService;
import com.kodlamaio.stockservice.business.dto.requests.creates.CreateCategoryRequest;
import com.kodlamaio.stockservice.business.dto.requests.updates.UpdateCategoryRequest;
import com.kodlamaio.stockservice.business.dto.responses.creates.CreateCategoryResponse;
import com.kodlamaio.stockservice.business.dto.responses.gets.GetAllCategoriesResponse;
import com.kodlamaio.stockservice.business.dto.responses.gets.GetCategoryResponse;
import com.kodlamaio.stockservice.business.dto.responses.updates.UpdateCategoryResponse;
import com.kodlamaio.stockservice.business.rules.CategoryBusinessRules;
import com.kodlamaio.stockservice.entities.Category;
import com.kodlamaio.stockservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
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
        var categories = repository.findAll();
        var responses = categories
                .stream()
                .map(category -> mapper.forResponse().map(category, GetAllCategoriesResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetCategoryResponse getById(UUID id) {
        rules.checkIfCategoryExists(id);

        var category = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(category, GetCategoryResponse.class);

        return response;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        rules.CheckIfCategoryNameLength(request.getName());

        var category = mapper.forRequest().map(request, Category.class);
        category.setId(UUID.randomUUID());
        repository.save(category);
        var response = mapper.forResponse().map(category, CreateCategoryResponse.class);

        return response;
    }

    @Override
    public UpdateCategoryResponse update(UUID id, UpdateCategoryRequest request) {
        rules.checkIfCategoryExists(id);

        var category = mapper.forRequest().map(request, Category.class);
        category.setId(id);
        repository.save(category);
        var response = mapper.forResponse().map(category, UpdateCategoryResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCategoryExists(id);

        repository.deleteById(id);
    }
}