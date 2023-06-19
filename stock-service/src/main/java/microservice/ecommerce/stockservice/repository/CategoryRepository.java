package microservice.ecommerce.stockservice.repository;

import microservice.ecommerce.stockservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {}