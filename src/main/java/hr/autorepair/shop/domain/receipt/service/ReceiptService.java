package hr.autorepair.shop.domain.receipt.service;

import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;

import java.util.List;

public interface ReceiptService {
    List<ReceiptResponse> getAllReceipts();
}
