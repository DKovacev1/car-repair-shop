package hr.autorepair.shop.domain.receipt.service;

import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
import hr.autorepair.shop.domain.receipt.repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;
    private ModelMapper modelMapper;

    @Override
    public List<ReceiptResponse> getAllReceipts() {
        return receiptRepository.findAll().stream()
                .map(receipt -> modelMapper.map(receipt, ReceiptResponse.class))
                .toList();
    }

}
