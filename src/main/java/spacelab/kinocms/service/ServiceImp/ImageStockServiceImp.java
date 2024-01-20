package spacelab.kinocms.service.ServiceImp;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.repository.ImageStocksRepository;
import spacelab.kinocms.service.ImageStockService;

import java.util.List;

@Service
public class ImageStockServiceImp implements ImageStockService {

    private final ImageStocksRepository imageStockRepository;

    public ImageStockServiceImp(ImageStocksRepository imageStockRepository) {
        this.imageStockRepository = imageStockRepository;
    }


    @Override
    public void saveImageStock(ImageStock imageStock) {
        imageStockRepository.save(imageStock);
    }

    @Override
    public ImageStock getImageStock(long id) {
        return imageStockRepository.findById(id).orElse(null);
    }

    @Override
    public ImageStock getLastImageStock() {
        return imageStockRepository.findAll().get(imageStockRepository.findAll().size() - 1);
    }

    @Override
    public List<ImageStock> getAllImagesStock() {
        return imageStockRepository.findAll();
    }

    @Override
    public List<ImageStock> getAllImagesStockByStock(Stock stock) {
        return imageStockRepository.findImageStockByStock(stock);
    }

    @Override
    public void deleteImageStock(long id) {
        imageStockRepository.deleteById(id);
    }

    @Override
    public void updateImageStock(ImageStock imageStock) {
        imageStockRepository.save(imageStock);
    }

}