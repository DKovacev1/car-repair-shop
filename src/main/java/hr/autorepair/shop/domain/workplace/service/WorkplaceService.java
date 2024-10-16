package hr.autorepair.shop.domain.workplace.service;

import hr.autorepair.shop.domain.workplace.dto.WorkPlaceResponse;

import java.util.List;

public interface WorkplaceService {
    List<WorkPlaceResponse> getWorkplaces();
}
