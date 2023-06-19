package microservice.ecommerce.invoiceservice.api.controllers;

import lombok.AllArgsConstructor;
import microservice.ecommerce.invoiceservice.business.abstracts.InvoiceService;
import microservice.ecommerce.invoiceservice.business.dto.responses.GetAllInvoicesResponse;
import microservice.ecommerce.invoiceservice.business.dto.responses.GetInvoiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable String id)
    { return service.getById(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id)
    { service.delete(id); }
}
