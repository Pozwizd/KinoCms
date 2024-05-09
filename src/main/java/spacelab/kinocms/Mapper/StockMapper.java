package spacelab.kinocms.Mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Images.ImageNewsDto;
import spacelab.kinocms.Dto.Images.ImageStockDto;
import spacelab.kinocms.Dto.StockDto;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.News;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.service.ImageStockService;
import spacelab.kinocms.service.StockService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StockMapper {
    private final StockService stockService;
    private final ImageStockService imageStockService;
    private final UploadFile uploadFile;

    public StockMapper(StockService stockService,
                       ImageStockService imageStockService,
                       UploadFile uploadFile) {
        this.stockService = stockService;
        this.imageStockService = imageStockService;
        this.uploadFile = uploadFile;
    }


    public Stock toEntity(StockDto stockDto) {
        Stock stockFromDb = stockService.getStock(stockDto.getId());
        if (stockFromDb.getId() == null){
            stockFromDb.setDateCreated(new Date(System.currentTimeMillis()));
        }
        stockFromDb.setName(stockDto.getName());
        stockFromDb.setDatePosting(stockDto.getDatePosting());
        stockFromDb.setDescription(stockDto.getDescription());
        stockFromDb.setLinkVideo(stockDto.getLinkVideo());
        stockFromDb.setSeoUrl(stockDto.getSeoUrl());
        stockFromDb.setSeoTitle(stockDto.getSeoTitle());
        stockFromDb.setSeoKeywords(stockDto.getSeoKeywords());
        stockFromDb.setSeoDescription(stockDto.getSeoDescription());


        stockService.updateStock(stockFromDb);

        if (stockDto.getImagesStock() != null) {
            List<Long> ids = stockDto
                    .getImagesStock().stream().map(ImageStockDto::getId).filter(Objects::nonNull).toList();

            stockFromDb.getImagesStock().stream().filter(imageStock -> !ids.contains(imageStock.getId()))
                    .forEach(imageStockService::deleteImageStock);
            stockFromDb.getImagesStock()
                    .removeIf(imageFilm -> !ids.contains(imageFilm.getId()));
        }

        if (stockDto.getMainImage().getSize() != 0) {
            stockFromDb.setMainImage(uploadFile.uploadFile(stockDto.getMainImage(), stockFromDb.getMainImage()));
        }

        if (stockDto.getImagesStock() != null) {
            for (ImageStockDto imageStockDto : stockDto.getImagesStock()) {
                ImageStock imageStock = imageStockDto.getId() == null ? new ImageStock() :  imageStockService.getImageStock(imageStockDto.getId());
                imageStock.setId(imageStockDto.getId());
                if (!imageStockDto.getUrl().isEmpty()) {
                    imageStock.setUrl(uploadFile.uploadFile(imageStockDto.getUrl(), imageStock.getUrl()));
                }
                imageStock.setStock(stockFromDb);
                imageStockService.updateImageStock(imageStock);
                stockFromDb.getImagesStock().add(imageStock);
            }
        } else {
            if (stockFromDb.getImagesStock() != null) {
                for (ImageStock imageStock : stockFromDb.getImagesStock()) {
                    imageStockService.deleteImageStock(imageStock);
                }
                stockFromDb.setImagesStock(new ArrayList<>());
            }
        }

        return stockFromDb;
    }
}
