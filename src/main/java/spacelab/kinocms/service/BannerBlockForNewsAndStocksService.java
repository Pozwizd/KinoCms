package spacelab.kinocms.service;

import spacelab.kinocms.model.BannerBlock;
import spacelab.kinocms.model.BannerBlockForNewsAndStocks;
import spacelab.kinocms.repository.BannerBlockForNewsAndStocksRepository;

import java.util.List;


public interface BannerBlockForNewsAndStocksService {


    public void saveBannerBlockForNewsAndStocks(BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks);

    public List<BannerBlockForNewsAndStocks> getAllBannerBlockForNewsAndStocks();

    public void deleteBannerBlockForNewsAndStocks(long id);

    public BannerBlockForNewsAndStocks getBannerBlockForNewsAndStocks(long id);

    public void updateBannerBlockForNewsAndStocks(BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks);

}
