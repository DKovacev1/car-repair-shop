package hr.autorepair.shop.domain.car.service;

import hr.autorepair.shop.domain.car.dto.CarResponse;
import hr.autorepair.shop.domain.car.repository.CarRepository;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CarResponse> getCars() {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();

        if(userPrincipal.isUser())
            return carRepository.findByCarOwner_IdAppUser(userPrincipal.getIdAppUser()).stream()
                    .map(car -> modelMapper.map(car, CarResponse.class))
                    .toList();
        else
            return carRepository.findAll().stream()
                    .map(car -> modelMapper.map(car, CarResponse.class))
                    .toList();
    }

}
