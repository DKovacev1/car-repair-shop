package hr.autorepair.shop.domain.carmodel;

import hr.autorepair.shop.domain.carmodel.service.CarModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car-model")
@AllArgsConstructor
public class CarModelController {

    private final CarModelService carModelService;

    @GetMapping
    public ResponseEntity<List<String>> getCarMakers(){
        return ResponseEntity.ok(carModelService.getCarModels());
    }

}
