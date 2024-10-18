package hr.autorepair.shop.domain.joborderstatus.service;

import hr.autorepair.shop.domain.joborderstatus.dto.JobOrderStatusResponse;

import java.util.List;

public interface JobOrderStatusService {
    List<JobOrderStatusResponse> getJobOrderStatuses();
}
