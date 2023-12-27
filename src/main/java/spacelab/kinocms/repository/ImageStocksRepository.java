package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.ImagesEntity.ImageStocks;

public interface ImageStocksRepository extends JpaRepository<ImageStocks, Long> {
}