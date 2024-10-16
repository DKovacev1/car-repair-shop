package hr.autorepair.shop.domain.car.service;

import hr.autorepair.shop.domain.car.dto.CarResponse;

import java.util.List;

public interface CarService {
    List<CarResponse> getCars();
}
