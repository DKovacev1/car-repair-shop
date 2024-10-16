package hr.autorepair.shop.domain.receipt;

import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
import hr.autorepair.shop.domain.receipt.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receipt")
@AllArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @GetMapping
    public ResponseEntity<List<ReceiptResponse>> getReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

}
