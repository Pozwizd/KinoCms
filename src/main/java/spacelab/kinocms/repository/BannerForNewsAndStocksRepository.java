package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import spacelab.kinocms.model.Banner;
import spacelab.kinocms.model.BannerBlock;
import spacelab.kinocms.model.BannerBlockForNewsAndStocks;
import spacelab.kinocms.model.BannerForNewsAndStocks;


@Repository
public interface BannerForNewsAndStocksRepository extends JpaRepository<BannerForNewsAndStocks, Long> {

}