package hr.autorepair.shop.domain.car.repository;

import hr.autorepair.shop.domain.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c from Car c where c.carOwner.idAppUser = ?1 and c.carOwner.isDeleted = false")
    List<Car> findByIdAppuser(Long idAppUser);
}
