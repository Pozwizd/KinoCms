package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;

import java.util.List;

@Service
public interface ImageFilmService {

    public void saveImageFilm(ImageFilm imageFilm);

    public void deleteImageFilm(Long id);

    public ImageFilm getImageFilmById(Long id);

    public ImageFilm getLastImageFilmByFilm(Film film);

    public List<ImageFilm> getAllImageFilm();

    public List<ImageFilm> getAllImageFilmByFilm(Film film);

    public void updateImageFilm(ImageFilm imageFilm);


}
