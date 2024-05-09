package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.repository.ImageStocksRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageStockServiceImpTest {

    @Mock
    private ImageStocksRepository imageStockRepository;

    @InjectMocks
    private ImageStockServiceImp imageStockService;


    @BeforeEach
    public void setUp() {
        reset(imageStockRepository);
    }

    @Test
    void saveImageStockTest() {
        ImageStock imageStock = new ImageStock();
        imageStockService.saveImageStock(imageStock);
        verify(imageStockRepository, times(1)).save(imageStock);
    }

    @Test
    void getImageStockTest() {
        long id = 1L;
        ImageStock expectedImageStock = new ImageStock();
        when(imageStockRepository.findById(id)).thenReturn(Optional.of(expectedImageStock));
        ImageStock actualImageStock = imageStockService.getImageStock(id);
        assertEquals(expectedImageStock, actualImageStock);
    }


    @Test
    void getLastImageStockTest() {
        List<ImageStock> imageStocks = Arrays.asList(new ImageStock(), new ImageStock(), new ImageStock());
        when(imageStockRepository.findAll()).thenReturn(imageStocks);
        ImageStock expectedImageStock = imageStocks.get(imageStocks.size() - 1);
        ImageStock actualImageStock = imageStockService.getLastImageStock();
        assertEquals(expectedImageStock, actualImageStock);
    }

    @Test
    void getAllImagesStockTest() {
        List<ImageStock> expectedImageStocks = Arrays.asList(new ImageStock(), new ImageStock());
        when(imageStockRepository.findAll()).thenReturn(expectedImageStocks);
        List<ImageStock> actualImageStocks = imageStockService.getAllImagesStock();
        assertEquals(expectedImageStocks, actualImageStocks);
    }

    @Test
    void getAllImagesStockByStockTest() {
        Stock stock = new Stock();
        List<ImageStock> expectedImageStocks = Arrays.asList(new ImageStock(), new ImageStock());
        when(imageStockRepository.findImageStockByStock(stock)).thenReturn(expectedImageStocks);
        List<ImageStock> actualImageStocks = imageStockService.getAllImagesStockByStock(stock);
        assertEquals(expectedImageStocks, actualImageStocks);
    }

    @Test
    void deleteImageStockTest() {
        long id = 1L;
        ImageStock imageStock = new ImageStock();
        imageStock.setId(id);
        imageStockService.deleteImageStock(imageStock);
        verify(imageStockRepository, times(1)).deleteById(id);
    }

    @Test
    void updateImageStockTest() {
        ImageStock imageStock = new ImageStock();
        imageStockService.updateImageStock(imageStock);
        verify(imageStockRepository, times(1)).save(imageStock);
    }
}