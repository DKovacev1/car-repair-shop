package hr.autorepair.shop.domain.workplace.service;

import hr.autorepair.shop.domain.workplace.dto.AddWorkplaceRequest;
import hr.autorepair.shop.domain.workplace.dto.UpdateWorkplaceRequest;
import hr.autorepair.shop.domain.workplace.dto.WorkplaceResponse;

import java.util.List;

public interface WorkplaceService {
    List<WorkplaceResponse> getWorkplaces();
    WorkplaceResponse getWorkplace(Long idWorkplace);
    WorkplaceResponse addWorkplace(AddWorkplaceRequest request);
    WorkplaceResponse updateWorkplace(Long idWorkplace, UpdateWorkplaceRequest request);
    void deactivateWorkplace(Long idWorkplace);
}
