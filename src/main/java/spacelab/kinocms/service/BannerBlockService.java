package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.BannerBlock;

import java.util.List;

@Service
public interface BannerBlockService {
    public void saveBannerBlock(BannerBlock bannerBlock);

    public List<BannerBlock> getAllBannerBlock();

    public void deleteBannerBlock(long id);

    public BannerBlock getBannerBlock(long id);

    public void updateBannerBlock(BannerBlock bannerBlock);
}
