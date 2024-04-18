package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.banners.BannerForNewsAndStocks;

import java.util.List;

@Service
public interface BannerForNewsAndStocksService {

    public BannerForNewsAndStocks getBannerForNewsAndStocksById(Long id);
    public void saveBannerForNewsAndStocks(BannerForNewsAndStocks bannerForNewsAndStocks);
    public void deleteBannerForNewsAndStocks(Long id);
    public void updateBannerForNewsAndStocks(BannerForNewsAndStocks bannerForNewsAndStocks);
    public List<BannerForNewsAndStocks> getAllBannerForNewsAndStocks();

    public BannerForNewsAndStocks getLastBannerForNewsAndStocks();
}
