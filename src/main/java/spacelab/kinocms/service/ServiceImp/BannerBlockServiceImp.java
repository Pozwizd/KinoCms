package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;


import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.banners.BannerBlock;
import spacelab.kinocms.repository.BannerBlockRepository;
import spacelab.kinocms.service.BannerBlockService;

@Service
@AllArgsConstructor
@Log4j2
public class BannerBlockServiceImp implements BannerBlockService {

    private final BannerBlockRepository  bannerBlockRepository;

    @Override
    public void saveBannerBlock(BannerBlock bannerBlock) {
        log.info("Save banner block: " + bannerBlock);
        bannerBlockRepository.save(bannerBlock);
    }


    @Override
    public void deleteBannerBlock(long id) {
        log.info("Delete banner block by id: " + id);
        bannerBlockRepository.deleteById(id);
    }

    @Override
    public BannerBlock getBannerBlock(long id) {
        log.info("Get banner block by id: " + id);
        return bannerBlockRepository.findById(id).orElse(null);
    }

    @Override
    public void updateBannerBlock(BannerBlock bannerBlock) {
        log.info("Update banner block: " + bannerBlock);
        bannerBlockRepository.save(bannerBlock);
    }
}
