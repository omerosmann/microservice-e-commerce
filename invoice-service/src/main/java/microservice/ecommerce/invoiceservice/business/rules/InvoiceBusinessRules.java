package microservice.ecommerce.invoiceservice.business.rules;

import lombok.AllArgsConstructor;
import microservice.ecommerce.commonpackage.utils.exceptions.BusinessException;
import microservice.ecommerce.invoiceservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    public void checkProductExists(String id)
    { if(!repository.existsById(id)) throw new BusinessException("INVOICE_NOT_EXISTS"); }
}
