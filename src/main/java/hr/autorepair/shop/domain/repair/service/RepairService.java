package hr.autorepair.shop.domain.repair.service;

import hr.autorepair.shop.domain.repair.dto.AddRepairRequest;
import hr.autorepair.shop.domain.repair.dto.RepairResponse;
import hr.autorepair.shop.domain.repair.dto.UpdateRepairRequest;

import java.util.List;

public interface RepairService {
    List<RepairResponse> getRepairs();
    RepairResponse getRepair(Long idRepair);
    RepairResponse addRepair(AddRepairRequest request);
    RepairResponse updateRepair(Long idRepair, UpdateRepairRequest request);
    void deactivateRepair(Long idRepair);
}
