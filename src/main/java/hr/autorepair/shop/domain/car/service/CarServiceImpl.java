package hr.autorepair.shop.domain.car.service;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.domain.car.dto.AddCarRequest;
import hr.autorepair.shop.domain.car.dto.CarResponse;
import hr.autorepair.shop.domain.car.dto.UpdateCarRequest;
import hr.autorepair.shop.domain.car.model.Car;
import hr.autorepair.shop.domain.car.repository.CarRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static hr.autorepair.common.constants.MessageConstants.*;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CarResponse> getCars() {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser())
            return carRepository.findByIdAppuserAndIsDeletedFalse(userPrincipal.getAppUser().getIdAppUser()).stream()
                    .map(car -> modelMapper.map(car, CarResponse.class))
                    .toList();
        else
            return carRepository.findByIsDeletedFalse().stream()
                    .map(car -> modelMapper.map(car, CarResponse.class))
                    .toList();
    }

    @Override
    public CarResponse getCar(Long idCar) {
        Car car = findCar(idCar);

        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser() && !car.getCarOwner().getIdAppUser().equals(userPrincipal.getAppUser().getIdAppUser()))
            throw new BadRequestException(MessageFormat.format(CAR_NOT_EXISTS, idCar));

        return modelMapper.map(car, CarResponse.class);
    }

    @Override
    public CarResponse addCar(AddCarRequest request) {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        Long idAppUser;

        if(!userPrincipal.isUser()){
            if(request.getIdCarOwner() == null)
                idAppUser = userPrincipal.getAppUser().getIdAppUser();
            else
                idAppUser = request.getIdCarOwner();
        } else
            idAppUser = userPrincipal.getAppUser().getIdAppUser();

        AppUser appUser = appUserRepository.findById(idAppUser)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(USER_NOT_EXIST, idAppUser)));

        Car car = modelMapper.map(request, Car.class);
        car.setIsDeleted(false);
        car.setCarOwner(appUser);

        carRepository.save(car);
        return modelMapper.map(car, CarResponse.class);
    }

    @Override
    public CarResponse updateCar(Long idCar, UpdateCarRequest request) {
        Car car = findCar(idCar);

        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser() && !car.getCarOwner().getIdAppUser().equals(userPrincipal.getAppUser().getIdAppUser()))
            throw new BadRequestException(MessageFormat.format(CAR_NOT_EXISTS, idCar));

        if(!request.hasChanges(modelMapper.map(car, UpdateCarRequest.class)))
            throw new BadRequestException(NO_CHANGES_MADE);

        car.setMaker(request.getMaker());
        car.setModel(request.getModel());
        car.setCylinders(request.getCylinders());
        car.setDisplacement(request.getDisplacement());
        car.setYearOfProduction(request.getYearOfProduction());
        car.setFuelType(request.getFuelType());

        carRepository.save(car);
        return modelMapper.map(car, CarResponse.class);
    }

    @Override
    public void deactivateCar(Long idCar) {
        Car car = findCar(idCar);

        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser() && !car.getCarOwner().getIdAppUser().equals(userPrincipal.getAppUser().getIdAppUser()))
            throw new BadRequestException(MessageFormat.format(CAR_NOT_EXISTS, idCar));

        car.setIsDeleted(true);
        carRepository.save(car);
    }

    private Car findCar(Long idCar){
        return carRepository.findByIdCarAndIsDeletedFalse(idCar)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(CAR_NOT_EXISTS, idCar)));
    }

}
