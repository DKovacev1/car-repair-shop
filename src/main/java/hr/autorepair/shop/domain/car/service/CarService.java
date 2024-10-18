package hr.autorepair.shop.domain.car.service;

import hr.autorepair.shop.domain.car.dto.AddCarRequest;
import hr.autorepair.shop.domain.car.dto.CarResponse;
import hr.autorepair.shop.domain.car.dto.UpdateCarRequest;

import java.util.List;

public interface CarService {
    List<CarResponse> getCars();
    CarResponse getCar(Long idCar);
    CarResponse addCar(AddCarRequest request);
    CarResponse updateCar(Long idCar, UpdateCarRequest request);
    void deactivateCar(Long idCar);
}
