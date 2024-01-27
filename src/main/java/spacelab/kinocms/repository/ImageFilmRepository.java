package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;

import java.util.List;

public interface ImageFilmRepository extends JpaRepository<ImageFilm, Long> {
    ImageFilm findTopImageFilmByFilmOrderByIdDesc(Film film);

    List<ImageFilm> findAllImageFilmByFilm(Film film);
}