package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.ImagesEntity.ImageHall;

public interface ImageHallRepository extends JpaRepository<ImageHall, Long> {
}