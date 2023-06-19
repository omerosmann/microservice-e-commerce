package microservice.ecommerce.invoiceservice.business.abstracts;

import microservice.ecommerce.invoiceservice.business.dto.responses.GetAllInvoicesResponse;
import microservice.ecommerce.invoiceservice.business.dto.responses.GetInvoiceResponse;
import microservice.ecommerce.invoiceservice.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(String id);
    void add(Invoice invoice);
    void delete(String id);
}