package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {

    List<Hall> findAllByCinema(Cinema cinema);
}