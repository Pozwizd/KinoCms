package spacelab.kinocms.service.ServiceImp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.banners.Banner;
import spacelab.kinocms.entity.banners.BannerBlock;
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
    @Mock
    private UploadFile uploadFile;

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
//        long id = 1L;
//        bannerService.deleteBanner(id);
//        verify(bannerRepository).deleteById(id);

        // Arrange
        long bannerId = 1L;
        String imagePath = "path/to/image.jpg";
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setUrl(imagePath);

        when(bannerRepository.findById(bannerId)).thenReturn(Optional.of(banner));

        // Act
        bannerService.deleteBanner(bannerId);

        // Assert
        verify(bannerRepository, times(1)).findById(bannerId);
        verify(uploadFile, times(1)).deleteFile(banner.getUrl());
        verify(bannerRepository, times(1)).deleteById(bannerId);
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