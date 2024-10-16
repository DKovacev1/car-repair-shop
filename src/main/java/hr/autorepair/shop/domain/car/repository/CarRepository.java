package hr.autorepair.shop.domain.car.repository;

import hr.autorepair.shop.domain.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByCarOwner_IdAppUser(Long idAppUser);
    Car save(Car car);
}
