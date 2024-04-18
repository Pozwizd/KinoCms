package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Cinema findTopCinemaByOrderByIdDesc();
}