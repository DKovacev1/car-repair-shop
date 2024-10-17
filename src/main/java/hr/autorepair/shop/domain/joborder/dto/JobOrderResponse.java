package hr.autorepair.shop.domain.joborder.dto;

import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.car.dto.CarResponse;
import hr.autorepair.shop.domain.repair.dto.RepairResponse;
import hr.autorepair.shop.domain.workplace.dto.WorkPlaceResponse;
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
    private Boolean isFinished;
    private WorkPlaceResponse workplace;
    private AppUserResponse jobOrderAppUserEmployee;//which employee created the job order
    private Set<RepairResponse> repairs;
    private CarResponse car;
}
