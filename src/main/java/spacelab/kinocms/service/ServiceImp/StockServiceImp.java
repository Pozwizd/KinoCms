package spacelab.kinocms.service.ServiceImp;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.repository.StocksRepository;
import spacelab.kinocms.service.StockService;

@Service
public class StockServiceImp implements StockService {

    private final StocksRepository stocksRepository;

    public StockServiceImp(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }

    @Override
    public Iterable<Stock> listAllStocks() {
        return stocksRepository.findAll();
    }

    @Override
    public void saveStock(Stock stock) {
        stocksRepository.save(stock);
    }

    @Override
    public Stock getStock(long id) {
        return stocksRepository.findById(id).get();
    }

    @Override
    public Stock getLastStock() {
        return stocksRepository.findAll().get(stocksRepository.findAll().size() - 1);
    }


    @Override
    public void deleteStock(long id) {
        stocksRepository.deleteById(id);
    }

    @Override
    public void updateStock(Stock stock) {
        stocksRepository.save(stock);
    }
}
