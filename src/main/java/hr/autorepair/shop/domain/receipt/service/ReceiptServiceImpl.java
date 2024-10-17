package hr.autorepair.shop.domain.receipt.service;

import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
import hr.autorepair.shop.domain.receipt.repository.ReceiptRepository;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
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
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser())
            return receiptRepository.findByIdAppUser(userPrincipal.getIdAppUser()).stream()
                    .map(receipt -> modelMapper.map(receipt, ReceiptResponse.class))
                    .toList();
        else
            return receiptRepository.findAll().stream()
                    .map(receipt -> modelMapper.map(receipt, ReceiptResponse.class))
                    .toList();
    }

}
