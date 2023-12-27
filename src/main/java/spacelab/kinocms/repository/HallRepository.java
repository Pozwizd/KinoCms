package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {
}