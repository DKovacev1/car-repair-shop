package hr.autorepair.shop.domain.schedule;

import hr.autorepair.shop.domain.schedule.dto.ScheduleResponse;
import hr.autorepair.shop.domain.schedule.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getSchedule(){
        return ResponseEntity.ok(scheduleService.getScheduleForNext30Days());
    }

}
