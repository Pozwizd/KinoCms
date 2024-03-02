package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.banners.BannerBackground;

import java.util.List;

@Service
public interface BannerBackgroundService {

    public BannerBackground getBannerBackground(Long id);
    public void saveBannerBackground(BannerBackground bannerBackground);
    public void deleteBannerBackground(Long id);
    public void updateBannerBackground(BannerBackground bannerBackground);
    public List<BannerBackground> getAllBannerBackground();

}
