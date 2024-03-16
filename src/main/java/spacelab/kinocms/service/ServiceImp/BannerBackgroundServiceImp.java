package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.banners.BannerBackground;
import spacelab.kinocms.repository.BannerBackgroundRepository;
import spacelab.kinocms.service.BannerBackgroundService;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerBackgroundServiceImp implements BannerBackgroundService {

    private static final Logger logger = LogManager.getLogger(BannerBackgroundServiceImp.class);

    private final BannerBackgroundRepository  bannerBackgroundRepository;

    @Override
    public BannerBackground getBannerBackground(Long id) {
        logger.info("Get banner background by id: " + id);
        return bannerBackgroundRepository.findById(id).orElse(null);
    }

    @Override
    public void saveBannerBackground(BannerBackground bannerBackground) {
        logger.info("Save banner background: " + bannerBackground);
        bannerBackgroundRepository.save(bannerBackground);
    }

    @Override
    public void deleteBannerBackground(Long id) {
        logger.info("Delete banner background by id: " + id);
        bannerBackgroundRepository.deleteById(id);
    }

    @Override
    public void updateBannerBackground(BannerBackground bannerBackground) {
        logger.info("Update banner background: " + bannerBackground);
        bannerBackgroundRepository.save(bannerBackground);
    }

    @Override
    public List<BannerBackground> getAllBannerBackground() {
        logger.info("Get all banner background");
        return bannerBackgroundRepository.findAll();
    }
}
