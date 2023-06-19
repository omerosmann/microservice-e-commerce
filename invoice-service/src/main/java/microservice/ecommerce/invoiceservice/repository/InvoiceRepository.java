package microservice.ecommerce.invoiceservice.repository;

import microservice.ecommerce.invoiceservice.entities.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {}