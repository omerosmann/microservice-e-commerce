package com.kodlamaio.stockservice.business.abstracts;



import com.kodlamaio.stockservice.business.dto.requests.creates.CreateCategoryRequest;
import com.kodlamaio.stockservice.business.dto.requests.updates.UpdateCategoryRequest;
import com.kodlamaio.stockservice.business.dto.responses.creates.CreateCategoryResponse;
import com.kodlamaio.stockservice.business.dto.responses.gets.GetAllCategoriesResponse;
import com.kodlamaio.stockservice.business.dto.responses.gets.GetCategoryResponse;
import com.kodlamaio.stockservice.business.dto.responses.updates.UpdateCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<GetAllCategoriesResponse> getAll();
    GetCategoryResponse getById(UUID id);
    CreateCategoryResponse add(CreateCategoryRequest request);
    UpdateCategoryResponse update (UUID id, UpdateCategoryRequest request);
    void delete (UUID id);
}
