package hr.autorepair.shop.domain.car.repository;

import hr.autorepair.shop.domain.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c from Car c where c.carOwner.idAppUser = ?1 and c.isDeleted = false and c.carOwner.isDeleted = false")
    List<Car> findByIdAppuserAndIsDeletedFalse(Long idAppUser);
    @Query("select c from Car c where c.isDeleted = false and c.carOwner.isDeleted = false")
    List<Car> findByIsDeletedFalse();
    @Query("select c from Car c where c.isDeleted = false and c.carOwner.isDeleted = false and c.idCar = ?1")
    Optional<Car> findByIdCarAndIsDeletedFalse(Long idCar);
}
