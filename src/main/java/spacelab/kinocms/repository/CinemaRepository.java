package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Cinema findTopCinemaByOrderByIdDesc();
}