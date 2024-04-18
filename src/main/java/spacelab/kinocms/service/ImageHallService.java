package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.ImagesEntity.ImageHall;

import java.util.List;


@Service
public interface ImageHallService{
    public void saveImageHall(ImageHall  imageHall);
    public ImageHall getImageHall(Long id);
    public ImageHall getLastImageHallByHall(Hall hall);
    public List<ImageHall> getAllImageHall();
    public  List<ImageHall> getAllImageHallByHall(Hall hall);
    public void updateImageHall(ImageHall ImageHall);
    public void deleteImageHall(long id);

}