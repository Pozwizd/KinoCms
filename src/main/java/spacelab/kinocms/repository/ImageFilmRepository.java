package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.Film;
import spacelab.kinocms.entity.ImagesEntity.ImageFilm;

import java.util.List;

@Repository
public interface ImageFilmRepository extends JpaRepository<ImageFilm, Long> {
    ImageFilm findTopImageFilmByFilmOrderByIdDesc(Film film);

    List<ImageFilm> findAllImageFilmByFilm(Film film);
}