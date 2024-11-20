package hr.autorepair.shop.domain.carmaker.service;

import hr.autorepair.shop.domain.carmaker.dto.CarMakerResponse;

import java.util.List;

public interface CarMakerService {
    List<CarMakerResponse> getCarMakers();
}
