package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;

public interface ImageCinemaRepository extends JpaRepository<ImageCinema, Long> {
}