package microservice.ecommerce.saleservice.repository;

import microservice.ecommerce.saleservice.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {}