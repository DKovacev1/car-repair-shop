package hr.autorepair.shop.domain.carmodel;

import hr.autorepair.shop.domain.carmodel.dto.CarModelResponse;
import hr.autorepair.shop.domain.carmodel.service.CarModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/car-model")
@AllArgsConstructor
public class CarModelController {

    private final CarModelService carModelService;

    @GetMapping
    public ResponseEntity<List<CarModelResponse>> getCarMakers(@RequestParam(required = false) BigDecimal idCarMaker){
        return ResponseEntity.ok(carModelService.getCarModels(idCarMaker));
    }

}
