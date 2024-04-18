package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.StockDto;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.service.StockService;

import java.sql.Date;

@Service
public class StockMapper {
    private final StockService stockService;

    public StockMapper(StockService stockService) {
        this.stockService = stockService;
    }

    public StockDto toDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setStatus(stock.getStatus());
        stockDto.setName(stock.getName());
        stockDto.setDatePosting(stock.getDatePosting());
        stockDto.setDateCreated(stock.getDateCreated());
        stockDto.setDescription(stock.getDescription());
        stockDto.setLinkVideo(stock.getLinkVideo());
        stockDto.setSeoUrl(stock.getSeoUrl());
        stockDto.setSeoTitle(stock.getSeoTitle());
        stockDto.setSeoKeywords(stock.getSeoKeywords());
        stockDto.setSeoDescription(stock.getSeoDescription());
        return stockDto;
    }

    public Stock toEntity(StockDto stockDto) {
        Stock stock = stockService.getStock(stockDto.getId());
        if (stock == null) {
            stock = new Stock();
        }
        stock.setId(stockDto.getId());
        stock.setStatus(stockDto.getStatus());
        stock.setName(stockDto.getName());
        stock.setDatePosting(stockDto.getDatePosting());
        stock.setDescription(stockDto.getDescription());
        stock.setLinkVideo(stockDto.getLinkVideo());
        stock.setSeoUrl(stockDto.getSeoUrl());
        stock.setSeoTitle(stockDto.getSeoTitle());
        stock.setSeoKeywords(stockDto.getSeoKeywords());
        stock.setSeoDescription(stockDto.getSeoDescription());
        return stock;
    }
}
