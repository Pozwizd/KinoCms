package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.Hall;
import spacelab.kinocms.entity.ImagesEntity.ImageHall;

import java.util.List;

@Repository
public interface ImageHallRepository extends JpaRepository<ImageHall, Long> {
    ImageHall findTopImageHallByHallOrderByIdDesc(Hall hall);

    List<ImageHall> findAllImageHallByHall(Hall hall);
}