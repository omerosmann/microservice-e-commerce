package com.kodlamaio.invoiceservice.business.rules;

import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import com.kodlamaio.invoiceservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    public void checkProductExists(String id)
    { if(!repository.existsById(id)) throw new BusinessException("INVOICE_NOT_EXISTS"); }
}
