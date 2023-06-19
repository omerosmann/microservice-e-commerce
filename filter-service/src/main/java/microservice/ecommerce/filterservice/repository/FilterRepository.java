package microservice.ecommerce.filterservice.repository;

import microservice.ecommerce.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, String> {
    void deleteByProductId(UUID productId);
    void deleteAllByCategoryId(UUID categoryId);
    Filter findByProductId(UUID productId);
}