package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.repository.ImageStocksRepository;
import spacelab.kinocms.service.ImageStockService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageStockServiceImp implements ImageStockService {

    private final ImageStocksRepository imageStockRepository;

    private static final Logger logger = LogManager.getLogger(ImageStockServiceImp.class);

    @Override
    public void saveImageStock(ImageStock imageStock) {
        logger.info("Save image stock: " + imageStock);
        imageStockRepository.save(imageStock);
    }

    @Override
    public ImageStock getImageStock(long id) {
        logger.info("Get image stock by id: " + id);
        return imageStockRepository.findById(id).orElse(null);
    }

    @Override
    public ImageStock getLastImageStock() {
        logger.info("Get last image stock");
        return imageStockRepository.findAll().get(imageStockRepository.findAll().size() - 1);
    }

    @Override
    public List<ImageStock> getAllImagesStock() {
        logger.info("Get all image stock");
        return imageStockRepository.findAll();
    }

    @Override
    public List<ImageStock> getAllImagesStockByStock(Stock stock) {
        logger.info("Get all image stock by stock: " + stock);
        return imageStockRepository.findImageStockByStock(stock);
    }

    @Override
    public void deleteImageStock(ImageStock imageStock) {
        logger.info("Delete image stock: " + imageStock);
        imageStockRepository.deleteById(imageStock.getId());
    }

    @Override
    public void updateImageStock(ImageStock imageStock) {
        logger.info("Update image stock: " + imageStock);
        imageStockRepository.save(imageStock);
    }

}