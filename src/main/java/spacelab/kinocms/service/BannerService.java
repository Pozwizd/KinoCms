package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.banners.Banner;
import spacelab.kinocms.model.banners.BannerBlock;

import java.util.List;

@Service
public interface BannerService {

    void saveBanner(Banner banner);

    void deleteBanner(long id);

    void updateBanner(Banner banner);

    public Banner getBanner(long id);

    public List<Banner> getAllBanners();

    public Banner getLastBannerByBannerBlock(BannerBlock bannerBlock);

    void deleteBanner(Banner banner);
}
