package hr.autorepair.shop.domain.part.repository;

import hr.autorepair.shop.domain.part.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByIsDeletedFalse();
    Optional<Part> findByIdPartAndIsDeletedFalse(Long idPart);
}
