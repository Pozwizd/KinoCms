package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.ImagesEntity.ImageHall;

import java.util.List;

public interface ImageHallRepository extends JpaRepository<ImageHall, Long> {
    ImageHall findTopImageHallByHallOrderByIdDesc(Hall hall);

    List<ImageHall> findAllImageHallByHall(Hall hall);
}