package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;
import spacelab.kinocms.repository.ImageFilmRepository;
import spacelab.kinocms.service.ImageFilmService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageFilmServiceImp implements ImageFilmService {

    private final ImageFilmRepository imageFilmRepository;
    @Override
    public void saveImageFilm(ImageFilm imageFilm) {
        imageFilmRepository.save(imageFilm);
    }

    @Override
    public void deleteImageFilm(Long id) {
        imageFilmRepository.deleteById(id);
    }

    @Override
    public ImageFilm getImageFilmById(Long id) {
        return imageFilmRepository.findById(id).orElse(null);
    }

    @Override
    public ImageFilm getLastImageFilmByFilm(Film film) {
        return imageFilmRepository.findTopImageFilmByFilmOrderByIdDesc(film);
    }

    @Override
    public List<ImageFilm> getAllImageFilm() {
        return imageFilmRepository.findAll();
    }

    @Override
    public List<ImageFilm> getAllImageFilmByFilm(Film film) {
        return imageFilmRepository.findAllImageFilmByFilm(film);
    }

    @Override
    public void updateImageFilm(ImageFilm imageFilm) {
        imageFilmRepository.saveAndFlush(imageFilm);
    }

}
