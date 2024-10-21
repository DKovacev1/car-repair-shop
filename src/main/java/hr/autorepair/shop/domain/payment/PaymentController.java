package hr.autorepair.shop.domain.payment;

import hr.autorepair.shop.domain.payment.dto.PaymentResponse;
import hr.autorepair.shop.domain.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getPayments(){
        return ResponseEntity.ok(paymentService.getPayments());
    }

}
