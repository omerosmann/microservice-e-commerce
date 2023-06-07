package com.kodlamaio.saleservice.api.controllers;

import com.kodlamaio.saleservice.business.abstracts.SaleService;
import com.kodlamaio.saleservice.business.dto.requests.CreateSaleRequest;
import com.kodlamaio.saleservice.business.dto.requests.UpdateSaleRequest;
import com.kodlamaio.saleservice.business.dto.responses.CreateSaleResponse;
import com.kodlamaio.saleservice.business.dto.responses.GetAllSalesResponse;
import com.kodlamaio.saleservice.business.dto.responses.GetSaleResponse;
import com.kodlamaio.saleservice.business.dto.responses.UpdateSaleResponse;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sales")
public class SalesController {
    private final SaleService service;

    @GetMapping
    public List<GetAllSalesResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetSaleResponse getById(@PathVariable UUID id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateSaleResponse add(@RequestBody CreateSaleRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateSaleResponse update(@PathVariable UUID id, @RequestBody UpdateSaleRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    { service.delete(id); }
}