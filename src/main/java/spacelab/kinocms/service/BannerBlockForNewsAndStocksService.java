package spacelab.kinocms.service;

import spacelab.kinocms.entity.banners.BannerBlockForNewsAndStocks;


public interface BannerBlockForNewsAndStocksService {


    public void saveBannerBlockForNewsAndStocks(BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks);



    public void deleteBannerBlockForNewsAndStocks(long id);

    public BannerBlockForNewsAndStocks getBannerBlockForNewsAndStocks(long id);

    public void updateBannerBlockForNewsAndStocks(BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks);

}
