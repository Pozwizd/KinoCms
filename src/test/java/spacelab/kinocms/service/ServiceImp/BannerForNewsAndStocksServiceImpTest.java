package spacelab.kinocms.service.ServiceImp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.banners.BannerForNewsAndStocks;
import spacelab.kinocms.repository.BannerForNewsAndStocksRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BannerForNewsAndStocksServiceImpTest {

    private static final Logger logger = LogManager.getLogger(BannerForNewsAndStocksServiceImpTest.class);

    @Mock
    private BannerForNewsAndStocksRepository bannerForNewsAndStocksRepository;

    @InjectMocks
    private BannerForNewsAndStocksServiceImp bannerForNewsAndStocksService;

    @BeforeEach
    public void setUp() {
        reset(bannerForNewsAndStocksRepository);
    }

    @Test
    void testGetBannerForNewsAndStocksById() {
        Long id = 1L;
        BannerForNewsAndStocks expectedBanner = new BannerForNewsAndStocks();
        when(bannerForNewsAndStocksRepository.findById(id)).thenReturn(Optional.of(expectedBanner));
        BannerForNewsAndStocks result = bannerForNewsAndStocksService.getBannerForNewsAndStocksById(id);
        assertEquals(expectedBanner, result);
    }

    @Test
    void testSaveBannerForNewsAndStocks() {
        BannerForNewsAndStocks banner = new BannerForNewsAndStocks();
        bannerForNewsAndStocksService.saveBannerForNewsAndStocks(banner);
        verify(bannerForNewsAndStocksRepository).save(banner);
    }

    @Test
    void testDeleteBannerForNewsAndStocks() {
        Long id = 1L;
        bannerForNewsAndStocksService.deleteBannerForNewsAndStocks(id);
        verify(bannerForNewsAndStocksRepository).deleteById(id);
    }

    @Test
    void testUpdateBannerForNewsAndStocks() {
        BannerForNewsAndStocks banner = new BannerForNewsAndStocks();
        bannerForNewsAndStocksService.updateBannerForNewsAndStocks(banner);
        verify(bannerForNewsAndStocksRepository).save(banner);
    }

    @Test
    void testGetAllBannerForNewsAndStocks() {
        List<BannerForNewsAndStocks> expectedBanners = Arrays.asList(new BannerForNewsAndStocks(), new BannerForNewsAndStocks());
        when(bannerForNewsAndStocksRepository.findAll()).thenReturn(expectedBanners);
        List<BannerForNewsAndStocks> result = bannerForNewsAndStocksService.getAllBannerForNewsAndStocks();
        assertEquals(expectedBanners, result);
    }

    @Test
    void testGetLastBannerForNewsAndStocks() {
        BannerForNewsAndStocks expectedBanner = new BannerForNewsAndStocks();
        when(bannerForNewsAndStocksRepository.findLastBannerForNewsAndStocks()).thenReturn(expectedBanner);
        BannerForNewsAndStocks result = bannerForNewsAndStocksService.getLastBannerForNewsAndStocks();
        assertEquals(expectedBanner, result);
    }
}