package spacelab.kinocms.service.ServiceImp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.entity.banners.BannerBackground;
import spacelab.kinocms.repository.BannerBackgroundRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BannerBackgroundServiceImpTest {

    @Mock
    private BannerBackgroundRepository bannerBackgroundRepository;
    private static final Logger logger = LogManager.getLogger(BannerBackgroundServiceImpTest.class);
    @InjectMocks
    private BannerBackgroundServiceImp bannerBackgroundService;

    @BeforeEach
    public void setUp() {
        reset(bannerBackgroundRepository);
    }

    @Test
    public void testGetBannerBackground() {
        logger.info("Get banner background by id: " + 1L);
        logger.info("work " + 2L);
        logger.error("This is a simple message at ERROR level. " +
                "This is the minimum visible level.");        BannerBackground bannerBackground = new BannerBackground(1L, true, "background1.jpg");
        when(bannerBackgroundRepository.findById(1L)).thenReturn(Optional.of(bannerBackground));
        BannerBackground result = bannerBackgroundService.getBannerBackground(1L);
        assertNotNull(result);
        assertEquals(bannerBackground, result);
    }

    @Test
    public void testSaveBannerBackground() {
        BannerBackground bannerBackground = new BannerBackground(1L, true, "background1.jpg");
        bannerBackgroundService.saveBannerBackground(bannerBackground);
        verify(bannerBackgroundRepository, times(1)).save(bannerBackground);
    }

    @Test
    public void testDeleteBannerBackground() {
        bannerBackgroundService.deleteBannerBackground(1L);
        verify(bannerBackgroundRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateBannerBackground() {
        BannerBackground bannerBackground = new BannerBackground(1L, true, "background1.jpg");
        bannerBackgroundService.updateBannerBackground(bannerBackground);
        verify(bannerBackgroundRepository, times(1)).save(bannerBackground);
    }

    @Test
    public void testGetAllBannerBackground() {
        BannerBackground bannerBackground1 = new BannerBackground(1L, true, "background1.jpg");
        BannerBackground bannerBackground2 = new BannerBackground(2L, false, "background2.png");
        List<BannerBackground> bannerBackgrounds = Arrays.asList(bannerBackground1, bannerBackground2);
        when(bannerBackgroundRepository.findAll()).thenReturn(bannerBackgrounds);

        List<BannerBackground> result = bannerBackgroundService.getAllBannerBackground();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(bannerBackground1));
        assertTrue(result.contains(bannerBackground2));
    }
}