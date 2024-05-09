package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.Stock;

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