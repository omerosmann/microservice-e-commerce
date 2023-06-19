package microservice.ecommerce.saleservice.business.abstracts;

import microservice.ecommerce.saleservice.business.dto.requests.CreateSaleRequest;
import microservice.ecommerce.saleservice.business.dto.requests.UpdateSaleRequest;
import microservice.ecommerce.saleservice.business.dto.responses.CreateSaleResponse;
import microservice.ecommerce.saleservice.business.dto.responses.GetAllSalesResponse;
import microservice.ecommerce.saleservice.business.dto.responses.GetSaleResponse;
import microservice.ecommerce.saleservice.business.dto.responses.UpdateSaleResponse;

import java.util.List;
import java.util.UUID;

public interface SaleService {
    List<GetAllSalesResponse> getAll();
    GetSaleResponse getById(UUID id);
    CreateSaleResponse add(CreateSaleRequest request);
    UpdateSaleResponse update(UUID id, UpdateSaleRequest request);
    void delete(UUID id);
}