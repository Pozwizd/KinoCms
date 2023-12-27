package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;

public interface ImageFilmRepository extends JpaRepository<ImageFilm, Long> {
}