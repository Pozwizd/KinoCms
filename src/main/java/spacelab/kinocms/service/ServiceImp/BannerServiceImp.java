package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Banner;
import spacelab.kinocms.model.BannerBlock;
import spacelab.kinocms.repository.BannerRepository;
import spacelab.kinocms.service.BannerService;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerServiceImp implements BannerService {

    private final BannerRepository bannerRepository;

    @Override
    public void saveBanner(Banner banner) {
        bannerRepository.save(banner);
    }

    @Override
    public void deleteBanner(long id) {
        bannerRepository.deleteById(id);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerRepository.save(banner);
    }

    @Override
    public Banner getBanner(long id) {
        return bannerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner getLastBannerByBannerBlock(BannerBlock bannerBlock) {
        return bannerRepository.findTopBannerByBannerBlockOrderByIdDesc(bannerBlock);
    }
}
