package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.banners.Banner;
import spacelab.kinocms.model.banners.BannerBlock;
import spacelab.kinocms.repository.BannerRepository;
import spacelab.kinocms.service.BannerService;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerServiceImp implements BannerService {

    private final BannerRepository bannerRepository;
    private static final Logger logger = LogManager.getLogger(BannerServiceImp.class);

    @Override
    public void saveBanner(Banner banner) {
        logger.info("Save banner: " + banner);
        bannerRepository.save(banner);
    }

    @Override
    public void deleteBanner(long id) {
        logger.info("Delete banner by id: " + id);
        bannerRepository.deleteById(id);
    }

    @Override
    public void updateBanner(Banner banner) {
        logger.info("Update banner: " + banner);
        bannerRepository.save(banner);
    }

    @Override
    public Banner getBanner(long id) {
        logger.info("Get banner by id: " + id);
        return bannerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Banner> getAllBanners() {
        logger.info("Get all banners");
        return bannerRepository.findAll();
    }

    @Override
    public Banner getLastBannerByBannerBlock(BannerBlock bannerBlock) {
        logger.info("Get last banner by banner block: " + bannerBlock);
        return bannerRepository.findTopBannerByBannerBlockOrderByIdDesc(bannerBlock);
    }
}
