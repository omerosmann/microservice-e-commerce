package microservice.ecommerce.stockservice.business.concretes;

import lombok.AllArgsConstructor;
import microservice.ecommerce.commonpackage.dto.GetProductResponse;
import microservice.ecommerce.commonpackage.events.stock.ProductCreatedEvent;
import microservice.ecommerce.commonpackage.events.stock.ProductDeletedEvent;
import microservice.ecommerce.commonpackage.kafka.producer.KafkaProducer;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import microservice.ecommerce.stockservice.business.abstracts.ProductService;
import microservice.ecommerce.stockservice.business.dto.requests.creates.CreateProductRequest;
import microservice.ecommerce.stockservice.business.dto.requests.updates.UpdateProductRequest;
import microservice.ecommerce.stockservice.business.dto.responses.creates.CreateProductResponse;
import microservice.ecommerce.stockservice.business.dto.responses.gets.GetAllProductsResponse;
import microservice.ecommerce.stockservice.business.dto.responses.updates.UpdateProductResponse;
import microservice.ecommerce.stockservice.business.rules.ProductBusinessRules;
import microservice.ecommerce.stockservice.entities.Product;
import microservice.ecommerce.stockservice.entities.enums.State;
import microservice.ecommerce.stockservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final ModelMapperService mapper;
    private final ProductBusinessRules rules;
    private final KafkaProducer producer;

    @Override
    public List<GetAllProductsResponse> getAll(boolean includeState) {
        List<Product> products = filterProductByState(includeState);

        List<GetAllProductsResponse> responses = products
                .stream()
                .map(product -> mapper.forResponse().map(product, GetAllProductsResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        rules.checkIfProductExists(id);

        Product product = repository.findById(id).orElseThrow();

        GetProductResponse response = mapper.forResponse().map(product, GetProductResponse.class);

        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        rules.checkIfPriceValue(request.getPrice());
        rules.checkIfQuantityValue(request.getQuantity());
        rules.checkIfDescriptionValue(request.getDescription());

        Product product = mapper.forRequest().map(request, Product.class);

        product.setId(UUID.randomUUID());
        product.setState(State.Active);

        Product createdProduct = repository.save(product);
        sendKafkaProductCreatedEvent(createdProduct);

        CreateProductResponse response = mapper.forResponse().map(product, CreateProductResponse.class);

        return response;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        rules.checkIfProductExists(id);
        rules.checkIfPriceValue(request.getPrice());
        rules.checkIfQuantityValue(request.getQuantity());
        rules.checkIfDescriptionValue(request.getDescription());

        Product product = mapper.forRequest().map(request, Product.class);

        product.setId(id);
        repository.save(product);

        UpdateProductResponse response = mapper.forResponse().map(product, UpdateProductResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfProductExists(id);

        repository.deleteById(id);

        sendKafkaProductDeletedEvent(id);
    }

    @Override
    public GetProductResponse stateByChange(UUID id) {
        rules.checkIfProductExists(id);

        Product product = repository.findById(id).orElseThrow();

        product.setId(id);
        stateChange(product);
        repository.save(product);

        GetProductResponse response = mapper.forResponse().map(product, GetProductResponse.class);

        return response;
    }

    @Override
    public void changeStateByProductId(State state, UUID id)
    { repository.changeStateByProductId(state, id); }

//  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    private void stateChange(Product product) {
        if (product.getState().equals(State.Active)) { product.setState(State.Passive); }
        else { product.setState(State.Active); }
    }

    private List<Product> filterProductByState(boolean includeState) {
        if (includeState) { return repository.findAll(); }

        return repository.findAllByStateIsNot(State.Passive);
    }

    private void sendKafkaProductCreatedEvent(Product createdProduct){
        ProductCreatedEvent event = mapper.forRequest().map(createdProduct, ProductCreatedEvent.class);
        producer.sendMessage(event, "product-created");
    }

    private void sendKafkaProductDeletedEvent(UUID id)
    { producer.sendMessage(new ProductDeletedEvent(id),"product-deleted"); }
}