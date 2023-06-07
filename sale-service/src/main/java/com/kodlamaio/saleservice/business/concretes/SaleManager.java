package com.kodlamaio.saleservice.business.concretes;

import com.kodlamaio.commonpackage.events.sale.SaleCreatedEvent;
import com.kodlamaio.commonpackage.events.sale.SaleDeletedEvent;
import com.kodlamaio.commonpackage.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.saleservice.api.clients.stock.StockClient;
import com.kodlamaio.saleservice.business.abstracts.SaleService;
import com.kodlamaio.saleservice.business.dto.requests.CreateSaleRequest;
import com.kodlamaio.saleservice.business.dto.requests.UpdateSaleRequest;
import com.kodlamaio.saleservice.business.dto.responses.CreateSaleResponse;
import com.kodlamaio.saleservice.business.dto.responses.GetAllSalesResponse;
import com.kodlamaio.saleservice.business.dto.responses.GetSaleResponse;
import com.kodlamaio.saleservice.business.dto.responses.UpdateSaleResponse;
import com.kodlamaio.saleservice.business.rules.SaleBusinessRules;
import com.kodlamaio.saleservice.entities.Sale;
import com.kodlamaio.saleservice.repository.SaleRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleManager implements SaleService {
    private final KafkaProducer producer;
    private final SaleBusinessRules rules;
    private final StockClient stockClient;
    private final ModelMapperService mapper;
    private final SaleRepository repository;

    @Override
    public List<GetAllSalesResponse> getAll() {
        var sales = repository.findAll();

        var responses = sales
                .stream()
                .map(sale -> mapper.forResponse().map(sale, GetAllSalesResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetSaleResponse getById(UUID id) {
        rules.checkIfSaleExists(id);

        var sale = repository.findById(id).orElseThrow();

        var response = mapper.forResponse().map(sale, GetSaleResponse.class);

        return response;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        rules.ensureProductIsActive(request.getProductId());
        var sale = mapper.forRequest().map(request, Sale.class);

        sale.setId(null);
        sale.setTotalPrice(getTotalPrice(sale));
        sale.setSaleTime(LocalDateTime.now());

        //TODO: Payment

        repository.save(sale);

        // Sale
        sendKafkaSaleCreatedMessage(request.getProductId());

        //TODO: Invoice

        var response = mapper.forResponse().map(sale, CreateSaleResponse.class);

        return response;
    }

    @Override
    public UpdateSaleResponse update(UUID id, UpdateSaleRequest request) {
        rules.checkIfSaleExists(id);

        var sale = mapper.forRequest().map(request, Sale.class);

        sale.setId(id);
        repository.save(sale);

        var response = mapper.forResponse().map(sale, UpdateSaleResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfSaleExists(id);
        sendKafkaSaleDeletedMessage(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Sale sale)
    { return sale.getPrice() * sale.getQuantity(); }

    private void sendKafkaSaleCreatedMessage(UUID productId)
    { producer.sendMessage(new SaleCreatedEvent(productId), "sale-created"); }

    private void sendKafkaSaleDeletedMessage(UUID id){
        var productId = repository.findById(id).orElseThrow().getProductId();
        producer.sendMessage(new SaleDeletedEvent(productId), "sale-deleted");
    }


}
