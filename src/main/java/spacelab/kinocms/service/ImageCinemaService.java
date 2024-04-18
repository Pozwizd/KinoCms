package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;

import java.util.List;

@Service
public interface ImageCinemaService{
    public void saveImageCinema(ImageCinema imageCinema);
    public ImageCinema getImageCinema(Long id);
    public ImageCinema getLastImageCinemaByCinema(Cinema cinema);
    public List<ImageCinema> getAllImageCinema();
    public  List<ImageCinema> getAllImageCinemaByCinema(Cinema cinema);
    public void updateImageCinema(ImageCinema imageCinema);
    public void deleteImageCinema(long id);

}
