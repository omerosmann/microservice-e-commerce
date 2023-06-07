package com.kodlamaio.stockservice.business.rules;

import com.kodlamaio.stockservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryBusinessRules {
    private final CategoryRepository repository;

    public void checkIfCategoryExists(UUID id)
    { if (!repository.existsById(id)) throw new RuntimeException("Category not exists!"); }

    public void CheckIfCategoryNameLength (String name)
    { if (name.length() <= 0) throw new RuntimeException("Category name cannot be left blank!"); }
}