package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.Stock;

import java.util.List;

@Repository
public interface ImageStocksRepository extends JpaRepository<ImageStock, Long> {
    List<ImageStock> findImageStockByStock(Stock stock);
}