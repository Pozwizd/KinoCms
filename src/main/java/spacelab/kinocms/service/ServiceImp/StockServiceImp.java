package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.repository.StocksRepository;
import spacelab.kinocms.service.StockService;

@Service
@AllArgsConstructor
public class StockServiceImp implements StockService {

    private final StocksRepository stocksRepository;
    private static final Logger logger = LogManager.getLogger(StockServiceImp.class);

    @Override
    public Iterable<Stock> listAllStocks() {
        logger.info("Get all stocks");
        return stocksRepository.findAll();
    }

    @Override
    public void saveStock(Stock stock) {
        logger.info("Save stock: " + stock);
        stocksRepository.save(stock);
    }

    @Override
    public Stock getStock(long id) {
        logger.info("Get stock by id: " + id);
        return stocksRepository.findById(id).orElse(new Stock());
    }

    @Override
    public Stock getLastStock() {
        logger.info("Get last stock");
        return stocksRepository.findAll().get(stocksRepository.findAll().size() - 1);
    }


    @Override
    public void deleteStock(long id) {
        logger.info("Delete stock by id: " + id);
        stocksRepository.deleteById(id);
    }

    @Override
    public void updateStock(Stock stock) {
        logger.info("Update stock: {}", stock);
        stocksRepository.save(stock);
    }

    @Override
    public Long idLastStock() {
        logger.info("Get id last Stock");
        return stocksRepository.idLastFilm();
    }
}
