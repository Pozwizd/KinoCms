package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.banners.BannerForNewsAndStocks;


@Repository
public interface BannerForNewsAndStocksRepository extends JpaRepository<BannerForNewsAndStocks, Long> {

    @Query("SELECT b FROM BannerForNewsAndStocks b ORDER BY b.id DESC limit 1")
    BannerForNewsAndStocks findLastBannerForNewsAndStocks();

}