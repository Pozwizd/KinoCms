package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.banners.BannerBlockForNewsAndStocks;
import spacelab.kinocms.repository.BannerBlockForNewsAndStocksRepository;
import spacelab.kinocms.service.BannerBlockForNewsAndStocksService;

@Service
@AllArgsConstructor
public class BannerBlockForNewsAndStocksServiceImp implements BannerBlockForNewsAndStocksService {

    private final BannerBlockForNewsAndStocksRepository bannerBlockForNewsAndStocksRepository;
    private static final Logger logger = LogManager.getLogger(BannerBlockForNewsAndStocksServiceImp.class);

    @Override
    public void saveBannerBlockForNewsAndStocks(BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks) {
        logger.info("Save banner block for news and stocks: " + bannerBlockForNewsAndStocks);
        bannerBlockForNewsAndStocksRepository.save(bannerBlockForNewsAndStocks);
    }



    @Override
    public void deleteBannerBlockForNewsAndStocks(long id) {
        logger.info("Delete banner block for news and stocks by id: " + id);
        bannerBlockForNewsAndStocksRepository.deleteById(id);
    }

    @Override
    public BannerBlockForNewsAndStocks getBannerBlockForNewsAndStocks(long id) {
        logger.info("Get banner block for news and stocks by id: " + id);
        return bannerBlockForNewsAndStocksRepository.findById(id).orElse(null);
    }

    @Override
    public void updateBannerBlockForNewsAndStocks(BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks) {
        logger.info("Update banner block for news and stocks: " + bannerBlockForNewsAndStocks);
        bannerBlockForNewsAndStocksRepository.save(bannerBlockForNewsAndStocks);
    }
}
