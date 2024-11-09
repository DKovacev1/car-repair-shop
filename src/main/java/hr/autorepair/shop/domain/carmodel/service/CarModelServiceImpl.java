package hr.autorepair.shop.domain.carmodel.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService{

    @Override
    public List<String> getCarModels() {
        return List.of();
    }

}
