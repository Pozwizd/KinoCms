package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;

import java.util.List;

public interface ImageCinemaRepository extends JpaRepository<ImageCinema, Long> {

    ImageCinema findTopImageCinemaByCinemaOrderByIdDesc(Cinema cinema);
    List<ImageCinema> findAllByCinema(Cinema cinema);
}