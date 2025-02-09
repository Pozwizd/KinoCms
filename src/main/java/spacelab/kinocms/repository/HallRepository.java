package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.entity.Hall;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    List<Hall> findAllByCinema(Cinema cinema);

    @Query("SELECT MAX(h.id) FROM Hall h")
    Long idLastHall();
}