package hr.autorepair.shop.domain.payment.service;

import hr.autorepair.shop.domain.payment.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> getPayments();
}
