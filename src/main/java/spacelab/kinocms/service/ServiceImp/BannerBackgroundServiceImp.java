package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.banners.BannerBackground;
import spacelab.kinocms.repository.BannerBackgroundRepository;
import spacelab.kinocms.service.BannerBackgroundService;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerBackgroundServiceImp implements BannerBackgroundService {

    private final BannerBackgroundRepository  bannerBackgroundRepository;

    @Override
    public BannerBackground getBannerBackground(Long id) {
        return bannerBackgroundRepository.findById(id).orElse(null);
    }

    @Override
    public void saveBannerBackground(BannerBackground bannerBackground) {
        bannerBackgroundRepository.save(bannerBackground);
    }

    @Override
    public void deleteBannerBackground(Long id) {
        bannerBackgroundRepository.deleteById(id);
    }

    @Override
    public void updateBannerBackground(BannerBackground bannerBackground) {
        bannerBackgroundRepository.save(bannerBackground);
    }

    @Override
    public List<BannerBackground> getAllBannerBackground() {
        return bannerBackgroundRepository.findAll();
    }
}
