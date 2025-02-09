package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.entity.ImagesEntity.ImageCinema;

import java.util.List;

@Repository
public interface ImageCinemaRepository extends JpaRepository<ImageCinema, Long> {

    ImageCinema findTopImageCinemaByCinemaOrderByIdDesc(Cinema cinema);
    List<ImageCinema> findAllByCinema(Cinema cinema);
}