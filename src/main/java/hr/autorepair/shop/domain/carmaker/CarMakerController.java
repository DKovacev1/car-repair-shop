package hr.autorepair.shop.domain.carmaker;

import hr.autorepair.shop.domain.carmaker.dto.CarMakerResponse;
import hr.autorepair.shop.domain.carmaker.service.CarMakerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car-maker")
@AllArgsConstructor
public class CarMakerController {

    private final CarMakerService carMakerService;

    @GetMapping
    public ResponseEntity<List<CarMakerResponse>> getCarMakers(){
        return ResponseEntity.ok(carMakerService.getCarMakers());
    }

}
