package hr.autorepair.shop.domain.payment.service;

import hr.autorepair.shop.domain.payment.dto.PaymentResponse;
import hr.autorepair.shop.domain.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PaymentResponse> getPayments() {
        return paymentRepository.findAll().stream()
                .map(payment -> modelMapper.map(payment, PaymentResponse.class))
                .toList();
    }

}
