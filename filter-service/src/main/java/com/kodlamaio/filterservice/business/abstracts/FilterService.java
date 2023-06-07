package com.kodlamaio.filterservice.business.abstracts;



import com.kodlamaio.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.dto.responses.GetFilterResponse;
import com.kodlamaio.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();
    GetFilterResponse getById(String id);
    void add(Filter filter);
    void delete(String id);
    void deleteByProductId(UUID productId);
    void deleteAllByCategoryId(UUID categoryId);
    Filter getByProductId(UUID productId);
}