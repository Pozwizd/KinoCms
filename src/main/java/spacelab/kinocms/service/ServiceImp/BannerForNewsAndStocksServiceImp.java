package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.banners.BannerForNewsAndStocks;
import spacelab.kinocms.repository.BannerForNewsAndStocksRepository;
import spacelab.kinocms.service.BannerForNewsAndStocksService;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerForNewsAndStocksServiceImp implements BannerForNewsAndStocksService {

    private final BannerForNewsAndStocksRepository bannerForNewsAndStocksRepository;
    private static final Logger logger = LogManager.getLogger(BannerForNewsAndStocksServiceImp.class);
    @Override
    public BannerForNewsAndStocks getBannerForNewsAndStocksById(Long id) {
        logger.info("Get banner for news and stocks by id: " + id);
        return bannerForNewsAndStocksRepository.findById(id).orElse(new BannerForNewsAndStocks());
    }

    @Override
    public void saveBannerForNewsAndStocks(BannerForNewsAndStocks bannerForNewsAndStocks) {
        logger.info("Save banner for news and stocks: " + bannerForNewsAndStocks);
        bannerForNewsAndStocksRepository.save(bannerForNewsAndStocks);
    }

    @Override
    public void deleteBannerForNewsAndStocks(BannerForNewsAndStocks bannerForNewsAndStocks) {
        logger.info("Delete banner for news and stocks by id: {}", bannerForNewsAndStocks.toString());
        bannerForNewsAndStocksRepository.delete(bannerForNewsAndStocks);
    }

    @Override
    public void updateBannerForNewsAndStocks(BannerForNewsAndStocks bannerForNewsAndStocks) {
        logger.info("Update banner for news and stocks: " + bannerForNewsAndStocks);
        bannerForNewsAndStocksRepository.save(bannerForNewsAndStocks);
    }

    @Override
    public List<BannerForNewsAndStocks> getAllBannerForNewsAndStocks() {
        logger.info("Get all banners for news and stocks");
        return bannerForNewsAndStocksRepository.findAll();
    }

    @Override
    public BannerForNewsAndStocks getLastBannerForNewsAndStocks() {
        logger.info("Get last banner for news and stocks");
        return bannerForNewsAndStocksRepository
                .findLastBannerForNewsAndStocks();
    }

}
