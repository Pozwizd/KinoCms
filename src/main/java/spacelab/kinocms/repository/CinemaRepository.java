package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Cinema findTopCinemaByOrderByIdDesc();

    @Query("SELECT MAX(c.id) FROM Cinema c")
    Long idLastCinema();
}