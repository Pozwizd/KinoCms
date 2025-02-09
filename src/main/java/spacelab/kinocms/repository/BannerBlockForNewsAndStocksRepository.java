package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.banners.BannerBlockForNewsAndStocks;

@Repository
public interface BannerBlockForNewsAndStocksRepository extends JpaRepository<BannerBlockForNewsAndStocks, Long> {
}