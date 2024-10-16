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
     * Vraca aute ovisno o pravama korisnika.
     * Za obicnog korisnika se vracaju samo njegovi auti,
     * dok admin i zaposlenik imaju pristup svim autima u bazi
     * @return lista automobila
     */
    @GetMapping
    public ResponseEntity<List<CarResponse>> getCars(){
        return ResponseEntity.ok(carService.getCars());
    }

}
