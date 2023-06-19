package microservice.ecommerce.commonpackage.configuration.mappers;

import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperManager;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper(){ return new ModelMapper(); }

    @Bean
    public ModelMapperService getModelMapperService(ModelMapper mapper) { return new ModelMapperManager(mapper); }
}
