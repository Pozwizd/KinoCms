package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.entity.ImagesEntity.ImageCinema;
import spacelab.kinocms.repository.ImageCinemaRepository;
import spacelab.kinocms.service.ImageCinemaService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageCinemaServiceImp implements ImageCinemaService {

    private final ImageCinemaRepository  imageCinemaRepository;
    private static final Logger logger = LogManager.getLogger(HallServiceImp.class);


    @Override
    public void saveImageCinema(ImageCinema imageCinema) {
        imageCinemaRepository.save(imageCinema);
        logger.info("Save image cinema: " + imageCinema);
    }

    @Override
    public ImageCinema getImageCinema(Long id) {
        logger.info("Get imageCinema by id: " + id);
        return imageCinemaRepository.findById(id).orElse(null);
    }

    @Override
    public ImageCinema getLastImageCinemaByCinema(Cinema cinema) {
        logger.info("Get last image cinema by cinema: " + cinema);
        return imageCinemaRepository.findTopImageCinemaByCinemaOrderByIdDesc(cinema);
    }

    @Override
    public List<ImageCinema> getAllImageCinema() {
        logger.info("Get all image cinema");
        return imageCinemaRepository.findAll();
    }

    @Override
    public List<ImageCinema> getAllImageCinemaByCinema(Cinema cinema) {
        logger.info("Get all image cinema by cinema: " + cinema);
        return imageCinemaRepository.findAllByCinema(cinema);
    }

    @Override
    public void updateImageCinema(ImageCinema imageCinema) {
        logger.info("Update image cinema: " + imageCinema);
        imageCinemaRepository.save(imageCinema);
    }

    @Override
    public void deleteImageCinema(long  id) {
        logger.info("Delete image cinema by id: " + id);
        imageCinemaRepository.deleteById(id);
    }
}
