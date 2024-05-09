package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Stock;

@Service
public interface StockService {

    public Iterable<Stock> listAllStocks();

    public void saveStock(Stock stock);

    public Stock getStock(long id);

    public Stock getLastStock();

    public void deleteStock(long id);

    public void updateStock(Stock stock);

    public Long idLastStock();
}
