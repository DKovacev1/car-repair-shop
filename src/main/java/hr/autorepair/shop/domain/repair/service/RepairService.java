package hr.autorepair.shop.domain.repair.service;

import hr.autorepair.shop.domain.repair.dto.RepairResponse;

import java.util.List;

public interface RepairService {
    List<RepairResponse> getRepairs();
}
