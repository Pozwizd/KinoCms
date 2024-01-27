package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;
import spacelab.kinocms.repository.ImageCinemaRepository;
import spacelab.kinocms.service.ImageCinemaService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageCinemaServiceImp implements ImageCinemaService {

    private final ImageCinemaRepository  imageCinemaRepository;


    @Override
    public void saveImageCinema(ImageCinema imageCinema) {
        imageCinemaRepository.save(imageCinema);
    }

    @Override
    public ImageCinema getImageCinema(Long id) {
        return imageCinemaRepository.findById(id).orElse(null);
    }

    @Override
    public ImageCinema getLastImageCinemaByCinema(Cinema cinema) {
        return imageCinemaRepository.findTopImageCinemaByCinemaOrderByIdDesc(cinema);
    }

    @Override
    public List<ImageCinema> getAllImageCinema() {
        return imageCinemaRepository.findAll();
    }

    @Override
    public List<ImageCinema> getAllImageCinemaByCinema(Cinema cinema) {
        return imageCinemaRepository.findAllByCinema(cinema);
    }

    @Override
    public void updateImageCinema(ImageCinema imageCinema) {
        imageCinemaRepository.save(imageCinema);
    }

    @Override
    public void deleteImageCinema(long  id) {
        imageCinemaRepository.deleteById(id);
    }
}
