package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;
import spacelab.kinocms.repository.ImageFilmRepository;
import spacelab.kinocms.service.ImageFilmService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageFilmServiceImp implements ImageFilmService {

    private final ImageFilmRepository imageFilmRepository;
    private final UploadFile uploadFile;
    private static final Logger logger = LogManager.getLogger(ImageFilmServiceImp.class);
    @Override
    public void saveImageFilm(ImageFilm imageFilm) {
        logger.info("Save image film: " + imageFilm);
        imageFilmRepository.save(imageFilm);
    }

    @Override
    public void deleteImageFilm(Long id) {
        logger.info("Delete image film by id: " + id);
        imageFilmRepository.findById(id).ifPresent(imageFilm -> uploadFile.deleteFile(imageFilm.getUrl()));
        imageFilmRepository.deleteById(id);
    }

    @Override
    public ImageFilm getImageFilmById(Long id) {
        logger.info("Get image film by id: " + id);
        return imageFilmRepository.findById(id).orElse(null);
    }

    @Override
    public ImageFilm getLastImageFilmByFilm(Film film) {
        logger.info("Get last image film by film: " + film);
        return imageFilmRepository.findTopImageFilmByFilmOrderByIdDesc(film);
    }

    @Override
    public List<ImageFilm> getAllImageFilm() {
        logger.info("Get all image films");
        return imageFilmRepository.findAll();
    }

    @Override
    public List<ImageFilm> getAllImageFilmByFilm(Film film) {
        logger.info("Get all image films by film: " + film);
        return imageFilmRepository.findAllImageFilmByFilm(film);
    }

    @Override
    public void updateImageFilm(ImageFilm imageFilm) {
        logger.info("Update image film: " + imageFilm);
        imageFilmRepository.saveAndFlush(imageFilm);
    }

}
