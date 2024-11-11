package hr.autorepair.shop.domain.carmodel.service;

import hr.autorepair.shop.domain.carmodel.dto.CarModelResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CarModelService {
    List<CarModelResponse> getCarModels(BigDecimal idCarMaker);
}
