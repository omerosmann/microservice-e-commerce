package microservice.ecommerce.stockservice.repository;

import jakarta.transaction.Transactional;
import microservice.ecommerce.stockservice.entities.Product;
import microservice.ecommerce.stockservice.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>{
    List<Product> findAllByStateIsNot(State state);

    @Modifying
    @Transactional
    @Query(value = "update Product set state =:state where id =:id")
    void changeStateByProductId(State state, UUID id);
}