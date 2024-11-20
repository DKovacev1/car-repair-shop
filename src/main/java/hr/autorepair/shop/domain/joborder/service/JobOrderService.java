package hr.autorepair.shop.domain.joborder.service;

import hr.autorepair.shop.domain.joborder.dto.AddJobOrderRequest;
import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;

import java.util.List;

public interface JobOrderService {
    List<JobOrderResponse> getAllJobOrders();
    JobOrderResponse getJobOrder(Long idJobOrder);
    JobOrderResponse addJobOrder(AddJobOrderRequest request);
    JobOrderResponse incrementStatus(Long idJobOrder);
    void deactivateJobOrder(Long idJobOrder);
    ReceiptResponse getReceiptByIdJobOrder(Long idJobOrder);
}
