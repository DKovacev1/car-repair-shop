package hr.autorepair.shop.domain.car;

import hr.autorepair.shop.domain.car.dto.AddCarRequest;
import hr.autorepair.shop.domain.car.dto.CarResponse;
import hr.autorepair.shop.domain.car.dto.UpdateCarRequest;
import hr.autorepair.shop.domain.car.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    /**
     * Returns cars depending on user roles.
     * For regular user returns only cars owned by them,
     * while admin or employee have access to all cars in database
     * @return list of cars
     */
    @GetMapping
    public ResponseEntity<List<CarResponse>> getCars(){
        return ResponseEntity.ok(carService.getCars());
    }

    /**
     * Returns car for the given idCar. If admin or employee search for a car with specific id,
     * they will get any car that has not been deleted. If regular user wants to get a car
     * with specific id, it will return the found record only if it is users car and and has not
     * been deleted
     * @param idCar
     * @return Car
     */
    @GetMapping("/{idCar}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long idCar){
        return ResponseEntity.ok(carService.getCar(idCar));
    }

    /**
     * Adds car to application user. Regular user can only add car to himself.
     * Admin and employee can add cars to themselves, but also to other users
     * by specifying idCarOwner in request body
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<CarResponse> addCar(@RequestBody @Valid AddCarRequest request){
        return ResponseEntity.ok(carService.addCar(request));
    }

    /**
     * Updates car for given idCar. All attributes can be changed except carOwner.
     * @param request
     * @return
     */
    @PutMapping("/{idCar}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long idCar, @RequestBody @Valid UpdateCarRequest request){
        return ResponseEntity.ok(carService.updateCar(idCar, request));
    }

    @DeleteMapping("/{idCar}")
    public ResponseEntity<Void> deactivateCar(@PathVariable Long idCar){
        carService.deactivateCar(idCar);
        return ResponseEntity.noContent().build();
    }

}
