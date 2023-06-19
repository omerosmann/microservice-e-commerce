package microservice.ecommerce.invoiceservice.business.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.ecommerce.commonpackage.events.invoice.InvoiceCreatedEvent;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import microservice.ecommerce.invoiceservice.business.abstracts.InvoiceService;
import microservice.ecommerce.invoiceservice.entities.Invoice;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final InvoiceService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "invoice-created",
            groupId = "invoice-sale-create"
    )
    public void consume(InvoiceCreatedEvent event){
        Invoice invoice = mapper.forRequest().map(event, Invoice.class);

        service.add(invoice);
        log.info("Invoice created event consumed {}", event);
    }
}
