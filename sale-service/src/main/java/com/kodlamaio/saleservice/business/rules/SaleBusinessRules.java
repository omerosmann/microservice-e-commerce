package com.kodlamaio.saleservice.business.rules;

import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.saleservice.api.clients.stock.StockClient;
import com.kodlamaio.saleservice.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.ClientResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleBusinessRules {
    private final StockClient stockClient;
    private final SaleRepository repository;


    public void checkIfSaleExists(UUID id)
    { if (!repository.existsById(id)) throw new BusinessException("SALE_NOT_EXISTS"); }

    public void checkIfQuantity(int quantity)
    { if (quantity <= 0) throw new BusinessException("PRODUCT_NOT_IN_STOCK"); }


}