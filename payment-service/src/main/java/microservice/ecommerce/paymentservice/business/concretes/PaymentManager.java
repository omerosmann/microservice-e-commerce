package microservice.ecommerce.paymentservice.business.concretes;

import lombok.AllArgsConstructor;
import microservice.ecommerce.commonpackage.dto.CreateSalePaymentRequest;
import microservice.ecommerce.commonpackage.utils.dto.ClientResponse;
import microservice.ecommerce.commonpackage.utils.exceptions.BusinessException;
import microservice.ecommerce.commonpackage.utils.mappers.ModelMapperService;
import microservice.ecommerce.paymentservice.business.abstracts.PaymentService;
import microservice.ecommerce.paymentservice.business.abstracts.PosService;
import microservice.ecommerce.paymentservice.business.dto.dto.requests.CreatePaymentRequest;
import microservice.ecommerce.paymentservice.business.dto.dto.requests.UpdatePaymentRequest;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.CreatePaymentResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.GetAllPaymentsResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.GetPaymentResponse;
import microservice.ecommerce.paymentservice.business.dto.dto.responses.UpdatePaymentResponse;
import microservice.ecommerce.paymentservice.business.rules.PaymentBusinessRules;
import microservice.ecommerce.paymentservice.entities.Payment;
import microservice.ecommerce.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PosService posService;
    private final ModelMapperService mapper;
    private final PaymentBusinessRules rules;
    private final PaymentRepository repository;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();

        List<GetAllPaymentsResponse> responses = payments
                .stream()
                .map(payment -> mapper.forResponse().map(payment, GetAllPaymentsResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        rules.checkIfPaymentExists(id);

        Payment payment = repository.findById(id).orElseThrow();

        GetPaymentResponse response = mapper.forResponse().map(payment, GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request);

        Payment payment = mapper.forRequest().map(request, Payment.class);

        payment.setId(UUID.randomUUID());
        repository.save(payment);

        CreatePaymentResponse response = mapper.forResponse().map(payment, CreatePaymentResponse.class);

        return response;
    }

    @Override
    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);

        Payment payment = mapper.forRequest().map(request, Payment.class);

        payment.setId(id);
        repository.save(payment);

        UpdatePaymentResponse response = mapper.forResponse().map(payment, UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPaymentExists(id);

        repository.deleteById(id);
    }

    @Override
    public ClientResponse processSalePayment(CreateSalePaymentRequest request) {
        ClientResponse response = new ClientResponse();

        try {
            rules.checkIfPaymentIsValid(request);

            var payment = repository.findByCardNumber(request.getCardNumber());

            rules.checkIfBalanceIsEnough(request.getPrice(), payment.getBalance());

            posService.pay();

            payment.setBalance(payment.getBalance() - request.getPrice());
            repository.save(payment);

            response.setSuccess(true);
        } catch (BusinessException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
        return response;
    }
}
