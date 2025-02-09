package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.banners.Banner;
import spacelab.kinocms.entity.banners.BannerBlock;
import spacelab.kinocms.repository.BannerRepository;
import spacelab.kinocms.service.BannerService;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class BannerServiceImp implements BannerService {

    private final BannerRepository bannerRepository;
    private final UploadFile uploadFile;
    private static final Logger logger = LoggerFactory.getLogger(BannerServiceImp.class);

    @Override
    public void saveBanner(Banner banner) {
        log.info("Create new banner");
        logger.info("Save banner: " + banner);
        bannerRepository.save(banner);
    }

    @Override
    public void deleteBanner(long id) {
        logger.info("Delete banner by id: " + id);
        bannerRepository.findById(id).ifPresent(banner -> uploadFile.deleteFile(banner.getUrl()));
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
        return bannerRepository.findById(id).orElse(new Banner());
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

    @Override
    public void deleteBanner(Banner banner) {
        logger.info("Delete banner by id: " + banner.getId());
        bannerRepository.findById(banner.getId()).ifPresent(bannerPresent -> uploadFile.deleteFile(bannerPresent.getUrl()));
        bannerRepository.deleteById(banner.getId());
    }
}
