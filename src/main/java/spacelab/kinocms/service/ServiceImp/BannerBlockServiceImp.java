package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Banner;
import spacelab.kinocms.model.BannerBlock;
import spacelab.kinocms.repository.BannerBlockRepository;
import spacelab.kinocms.service.BannerBlockService;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerBlockServiceImp implements BannerBlockService {

    private final BannerBlockRepository  bannerBlockRepository;

    @Override
    public void saveBannerBlock(BannerBlock bannerBlock) {
        bannerBlockRepository.save(bannerBlock);
    }

    @Override
    public List<BannerBlock> getAllBannerBlock() {
        return bannerBlockRepository.findAll();
    }

    @Override
    public void deleteBannerBlock(long id) {
        bannerBlockRepository.deleteById(id);
    }

    @Override
    public BannerBlock getBannerBlock(long id) {
        return bannerBlockRepository.findById(id).orElse(null);
    }

    @Override
    public void updateBannerBlock(BannerBlock bannerBlock) {
        bannerBlockRepository.save(bannerBlock);
    }
}
