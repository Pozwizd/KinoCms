package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.banners.BannerBlock;
import spacelab.kinocms.repository.BannerBlockRepository;
import spacelab.kinocms.service.BannerBlockService;

@Service
@AllArgsConstructor
public class BannerBlockServiceImp implements BannerBlockService {

    private final BannerBlockRepository  bannerBlockRepository;
    private static final Logger logger = LogManager.getLogger(BannerBlockServiceImp.class);

    @Override
    public void saveBannerBlock(BannerBlock bannerBlock) {
        logger.info("Save banner block: " + bannerBlock);
        bannerBlockRepository.save(bannerBlock);
    }


    @Override
    public void deleteBannerBlock(long id) {
        logger.info("Delete banner block by id: " + id);
        bannerBlockRepository.deleteById(id);
    }

    @Override
    public BannerBlock getBannerBlock(long id) {
        logger.info("Get banner block by id: " + id);
        return bannerBlockRepository.findById(id).orElse(null);
    }

    @Override
    public void updateBannerBlock(BannerBlock bannerBlock) {
        logger.info("Update banner block: " + bannerBlock);
        bannerBlockRepository.save(bannerBlock);
    }
}
