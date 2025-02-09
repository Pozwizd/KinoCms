package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.entity.Stock;
import spacelab.kinocms.repository.StocksRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImpTest {

    @Mock
    private StocksRepository stocksRepository;

    @InjectMocks
    private StockServiceImp stockService;

    @BeforeEach
    public void setUp() {
        reset(stocksRepository);
    }

    @Test
    void testListAllStocks() {
        List<Stock> stocks = Arrays.asList(new Stock(), new Stock());
        when(stocksRepository.findAll()).thenReturn(stocks);
        Iterable<Stock> result = stockService.listAllStocks();
        assertEquals(stocks, result);
    }

    @Test
    void testSaveStock() {
        Stock stock = new Stock();
        stockService.saveStock(stock);
        verify(stocksRepository).save(stock);
    }

    @Test
    void testGetStock() {
        long id = 1L;
        Stock expectedStock = new Stock();
        when(stocksRepository.findById(id)).thenReturn(Optional.of(expectedStock));
        Stock result = stockService.getStock(id);
        assertEquals(expectedStock, result);
    }

    @Test
    void testGetLastStock() {
        List<Stock> stocks = Arrays.asList(new Stock(), new Stock());
        Stock expectedStock = stocks.get(stocks.size() - 1);
        when(stocksRepository.findAll()).thenReturn(stocks);
        Stock result = stockService.getLastStock();
        assertEquals(expectedStock, result);
    }

    @Test
    void testDeleteStock() {
        long id = 1L;
        stockService.deleteStock(id);
        verify(stocksRepository).deleteById(id);
    }

//    @Test
//    void testUpdateStock() {
//        Stock stock = new Stock();
//        stock.setId(1L);
//        when(stocksRepository.findById(1L)).thenReturn(Optional.of(stock));
//        stockService.updateStock(stock);
//        verify(stocksRepository).save(stock);
//    }
}