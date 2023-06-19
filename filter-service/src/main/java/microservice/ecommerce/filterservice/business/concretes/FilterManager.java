package microservice.ecommerce.filterservice.business.concretes;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import microservice.ecommerce.filterservice.business.abstracts.FilterService;
import microservice.ecommerce.filterservice.business.dto.responses.GetAllFiltersResponse;
import microservice.ecommerce.filterservice.business.dto.responses.GetFilterResponse;
import microservice.ecommerce.filterservice.entities.Filter;
import microservice.ecommerce.filterservice.repository.FilterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllFiltersResponse> getAll() {
        List<Filter> filters = repository.findAll();

        List<GetAllFiltersResponse> responses = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetFilterResponse getById(String id) {
        Filter filter = repository.findById(id).orElseThrow();

        GetFilterResponse response = mapper.forResponse().map(filter, GetFilterResponse.class);

        return response;
    }

    @Override
    public void add(Filter filter) { repository.save(filter); }

    @Override
    public void delete(String id) { repository.deleteById(id); }

    @Override
    public void deleteByProductId(UUID productId) { repository.deleteByProductId(productId); }

    @Override
    public void deleteAllByCategoryId(UUID categoryId) { repository.deleteAllByCategoryId(categoryId); }

    @Override
    public Filter getByProductId(UUID productId) { return repository.findByProductId(productId); }
}