package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.banners.BannerBlock;

@Service
public interface BannerBlockService {
    public void saveBannerBlock(BannerBlock bannerBlock);


    public void deleteBannerBlock(long id);

    public BannerBlock getBannerBlock(long id);

    public void updateBannerBlock(BannerBlock bannerBlock);
}
