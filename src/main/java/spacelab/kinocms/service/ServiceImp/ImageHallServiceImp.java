package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.Hall;
import spacelab.kinocms.entity.ImagesEntity.ImageHall;
import spacelab.kinocms.repository.ImageHallRepository;
import spacelab.kinocms.service.ImageHallService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageHallServiceImp implements ImageHallService {

    private final ImageHallRepository imageHallRepository;
    private static final Logger logger = LogManager.getLogger(ImageHallServiceImp.class);
    @Override
    public void saveImageHall(ImageHall imageHall) {
        imageHallRepository.save(imageHall);
        logger.info("Save image hall: " + imageHall);
    }

    @Override
    public ImageHall getImageHall(Long id) {
        logger.info("Get image hall by id: " + id);
        return imageHallRepository.findById(id).orElse(null);
    }

    @Override
    public ImageHall getLastImageHallByHall(Hall hall) {
        logger.info("Get last image hall by hall: " + hall);
        return imageHallRepository.findTopImageHallByHallOrderByIdDesc(hall);
    }

    @Override
    public List<ImageHall> getAllImageHall() {
        logger.info("Get all image hall");
        return imageHallRepository.findAll();
    }

    @Override
    public List<ImageHall> getAllImageHallByHall(Hall hall) {
        logger.info("Get all image hall by hall: " + hall);
        return imageHallRepository.findAllImageHallByHall(hall);
    }

    @Override
    public void updateImageHall(ImageHall ImageHall) {
        logger.info("Update image hall: " + ImageHall);
        imageHallRepository.save(ImageHall);
    }

    @Override
    public void deleteImageHall(long id) {
        logger.info("Delete image hall by id: " + id);
        imageHallRepository.deleteById(id);
    }
}
