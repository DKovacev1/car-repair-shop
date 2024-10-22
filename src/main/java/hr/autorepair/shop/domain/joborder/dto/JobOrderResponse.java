package hr.autorepair.shop.domain.joborder.dto;

import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.car.dto.CarResponse;
import hr.autorepair.shop.domain.joborderpart.dto.JobOrderPartResponse;
import hr.autorepair.shop.domain.joborderstatus.dto.JobOrderStatusResponse;
import hr.autorepair.shop.domain.repair.dto.RepairResponse;
import hr.autorepair.shop.domain.workplace.dto.WorkplaceResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
public class JobOrderResponse {
    private Long idJobOrder;
    private String description;
    private LocalDate orderDate;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private WorkplaceResponse workplace;
    private AppUserResponse jobOrderAppUserEmployee;//which employee created the job order
    private CarResponse car;
    private JobOrderStatusResponse jobOrderStatus;
    private Set<RepairResponse> repairs;
    private Set<JobOrderPartResponse> parts;
}
