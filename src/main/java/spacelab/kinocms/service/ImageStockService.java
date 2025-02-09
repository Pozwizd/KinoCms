package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.ImagesEntity.ImageStock;
import spacelab.kinocms.entity.Stock;

import java.util.List;

@Service
public interface ImageStockService {

    void saveImageStock(ImageStock imageStock);

    ImageStock getImageStock(long id);

    ImageStock getLastImageStock();

    List<ImageStock> getAllImagesStock();

    List<ImageStock> getAllImagesStockByStock(Stock stock);

    void deleteImageStock(ImageStock imageStock);

    void updateImageStock(ImageStock imageStock);

}