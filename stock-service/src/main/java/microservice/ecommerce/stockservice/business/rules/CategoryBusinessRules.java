package microservice.ecommerce.stockservice.business.rules;

import lombok.AllArgsConstructor;
import microservice.ecommerce.stockservice.repository.CategoryRepository;
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