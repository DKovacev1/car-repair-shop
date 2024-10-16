package hr.autorepair.shop.domain.car.repository;

import hr.autorepair.shop.domain.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car save(Car car);
}
