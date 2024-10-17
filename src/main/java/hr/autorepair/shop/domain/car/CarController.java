package hr.autorepair.shop.domain.car;

import hr.autorepair.shop.domain.car.dto.CarResponse;
import hr.autorepair.shop.domain.car.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    /**
     * Returns cars depending on user roles.
     * For regular user returns only cars owned by him/her,
     * while admin or employee have access to all cars in database
     * @return list of cars
     */
    @GetMapping
    public ResponseEntity<List<CarResponse>> getCars(){
        return ResponseEntity.ok(carService.getCars());
    }

}
