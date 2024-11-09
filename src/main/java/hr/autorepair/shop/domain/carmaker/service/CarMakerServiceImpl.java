package hr.autorepair.shop.domain.carmaker.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarMakerServiceImpl implements CarMakerService{

    @Override
    public List<String> getCarMakers() {
        return List.of();
    }

}
