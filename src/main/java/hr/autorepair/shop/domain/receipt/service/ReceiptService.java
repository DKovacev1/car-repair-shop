package hr.autorepair.shop.domain.receipt.service;

import hr.autorepair.shop.domain.receipt.dto.AddReceiptRequest;
import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;

import java.util.List;

public interface ReceiptService {
    List<ReceiptResponse> getAllReceipts();
    ReceiptResponse getReceipt(Long idReceipt);
    ReceiptResponse addReceipt(AddReceiptRequest request);
    void deactivateReceipt(Long idReceipt);
}
