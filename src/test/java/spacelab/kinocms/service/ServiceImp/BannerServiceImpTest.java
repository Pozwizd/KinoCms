package spacelab.kinocms.service.ServiceImp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.banners.Banner;
import spacelab.kinocms.model.banners.BannerBlock;
import spacelab.kinocms.repository.BannerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BannerServiceImpTest {

    private static final Logger logger = LogManager.getLogger(BannerServiceImpTest.class);

    @Mock
    private BannerRepository bannerRepository;

    @InjectMocks
    private BannerServiceImp bannerService;

    @BeforeEach
    public void setUp() {
        reset(bannerRepository);
    }


    @Test
    void testSaveBanner() {
        Banner banner = new Banner();
        bannerService.saveBanner(banner);
        verify(bannerRepository).save(banner);
    }

    @Test
    void testDeleteBanner() {
        long id = 1L;
        bannerService.deleteBanner(id);
        verify(bannerRepository).deleteById(id);
    }

    @Test
    void testUpdateBanner() {
        Banner banner = new Banner();
        bannerService.updateBanner(banner);
        verify(bannerRepository).save(banner);
    }

    @Test
    void testGetBanner() {
        long id = 1L;
        Banner expectedBanner = new Banner();
        when(bannerRepository.findById(id)).thenReturn(Optional.of(expectedBanner));
        Banner result = bannerService.getBanner(id);
        assertEquals(expectedBanner, result);
    }

    @Test
    void testGetAllBanners() {
        List<Banner> expectedBanners = Arrays.asList(new Banner(), new Banner(), new Banner());
        when(bannerRepository.findAll()).thenReturn(expectedBanners);
        List<Banner> result = bannerService.getAllBanners();
        assertEquals(expectedBanners, result);
    }

    @Test
    void testGetLastBannerByBannerBlock() {
        BannerBlock bannerBlock = new BannerBlock();
        Banner expectedBanner = new Banner();
        when(bannerRepository.findTopBannerByBannerBlockOrderByIdDesc(bannerBlock)).thenReturn(expectedBanner);
        Banner result = bannerService.getLastBannerByBannerBlock(bannerBlock);
        assertEquals(expectedBanner, result);
    }
}