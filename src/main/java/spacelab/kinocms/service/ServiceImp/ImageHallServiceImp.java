package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.ImagesEntity.ImageHall;
import spacelab.kinocms.repository.ImageHallRepository;
import spacelab.kinocms.service.ImageHallService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageHallServiceImp implements ImageHallService {

    private final ImageHallRepository imageHallRepository;
    @Override
    public void saveImageHall(ImageHall imageHall) {
        imageHallRepository.save(imageHall);
    }

    @Override
    public ImageHall getImageHall(Long id) {
        return imageHallRepository.findById(id).orElse(null);
    }

    @Override
    public ImageHall getLastImageHallByHall(Hall hall) {
        return imageHallRepository.findTopImageHallByHallOrderByIdDesc(hall);
    }

    @Override
    public List<ImageHall> getAllImageHall() {
        return imageHallRepository.findAll();
    }

    @Override
    public List<ImageHall> getAllImageHallByHall(Hall hall) {
        return imageHallRepository.findAllImageHallByHall(hall);
    }

    @Override
    public void updateImageHall(ImageHall ImageHall) {
        imageHallRepository.save(ImageHall);
    }

    @Override
    public void deleteImageHall(long id) {
        imageHallRepository.deleteById(id);
    }
}
