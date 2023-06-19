package microservice.ecommerce.invoiceservice.business.concretes;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import microservice.ecommerce.invoiceservice.business.abstracts.InvoiceService;
import microservice.ecommerce.invoiceservice.business.dto.responses.GetAllInvoicesResponse;
import microservice.ecommerce.invoiceservice.business.dto.responses.GetInvoiceResponse;
import microservice.ecommerce.invoiceservice.business.rules.InvoiceBusinessRules;
import microservice.ecommerce.invoiceservice.entities.Invoice;
import microservice.ecommerce.invoiceservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final ModelMapperService mapper;
    private final InvoiceBusinessRules rules;
    private final InvoiceRepository repository;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();

        List<GetAllInvoicesResponse> responses = invoices
                .stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoicesResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetInvoiceResponse getById(String id) {
        rules.checkProductExists(id);

        Invoice invoice = repository.findById(id).orElseThrow();

        GetInvoiceResponse response = mapper.forResponse().map(invoice, GetInvoiceResponse.class);

        return response;
    }

    @Override
    public void add(Invoice invoice) { repository.save(invoice); }

    @Override
    public void delete(String id) {
        rules.checkProductExists(id);

        repository.deleteById(id);
    }
}
