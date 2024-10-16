package hr.autorepair.shop.domain.joborder.service;

import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;

import java.util.List;

public interface JobOrderService {
    List<JobOrderResponse> getAllJobOrders();
}
