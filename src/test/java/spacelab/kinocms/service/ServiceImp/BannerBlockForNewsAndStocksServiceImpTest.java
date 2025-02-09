package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.entity.banners.BannerBlockForNewsAndStocks;
import spacelab.kinocms.entity.banners.BannerForNewsAndStocks;
import spacelab.kinocms.repository.BannerBlockForNewsAndStocksRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BannerBlockForNewsAndStocksServiceImpTest {

    @Mock
    private BannerBlockForNewsAndStocksRepository bannerBlockForNewsAndStocksRepository;

    @InjectMocks
    private BannerBlockForNewsAndStocksServiceImp bannerBlockForNewsAndStocksService;

    @BeforeEach
    public void setUp() {
        reset(bannerBlockForNewsAndStocksRepository);
    }

    @Test
    public void testSaveBannerBlockForNewsAndStocks() {
        BannerForNewsAndStocks bannerForNewsAndStocks = new BannerForNewsAndStocks();
        bannerForNewsAndStocks.setId(1L);
        bannerForNewsAndStocks.setTitle("Banner 1");
        bannerForNewsAndStocks.setUrl("https://example.com/banner1.jpg");

        new BannerBlockForNewsAndStocks();
        BannerBlockForNewsAndStocks bannerBlock = BannerBlockForNewsAndStocks
                .builder()
                .id(1L)
                .statusBlockBannerForNewsAndStocks(true)
                .banners(List.of(bannerForNewsAndStocks))
                .build();

        bannerBlockForNewsAndStocksService.saveBannerBlockForNewsAndStocks(bannerBlock);
        verify(bannerBlockForNewsAndStocksRepository, times(1)).save(bannerBlock);
    }



    @Test
    public void testDeleteBannerBlockForNewsAndStocks() {
        bannerBlockForNewsAndStocksService.deleteBannerBlockForNewsAndStocks(1L);
        verify(bannerBlockForNewsAndStocksRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetBannerBlockForNewsAndStocks() {
        BannerForNewsAndStocks bannerForNewsAndStocks = new BannerForNewsAndStocks();
        bannerForNewsAndStocks.setId(1L);
        bannerForNewsAndStocks.setTitle("Banner 1");
        bannerForNewsAndStocks.setUrl("https://example.com/banner1.jpg");

        new BannerBlockForNewsAndStocks();
        BannerBlockForNewsAndStocks bannerBlock = BannerBlockForNewsAndStocks
                .builder()
                .id(1L)
                .statusBlockBannerForNewsAndStocks(true)
                .banners(List.of(bannerForNewsAndStocks))
                .build();

        when(bannerBlockForNewsAndStocksRepository.findById(1L)).thenReturn(Optional.of(bannerBlock));
        BannerBlockForNewsAndStocks result
                = bannerBlockForNewsAndStocksService.getBannerBlockForNewsAndStocks(1L);
        assertNotNull(result);
        assertEquals(bannerBlock, result);
    }

    @Test
    public void testUpdateBannerBlockForNewsAndStocks() {
        BannerForNewsAndStocks bannerForNewsAndStocks = new BannerForNewsAndStocks();
        bannerForNewsAndStocks.setId(1L);
        bannerForNewsAndStocks.setTitle("Banner 1");
        bannerForNewsAndStocks.setUrl("https://example.com/banner1.jpg");

        new BannerBlockForNewsAndStocks();
        BannerBlockForNewsAndStocks bannerBlock = BannerBlockForNewsAndStocks
                .builder()
                .id(1L)
                .statusBlockBannerForNewsAndStocks(true)
                .banners(List.of(bannerForNewsAndStocks))
                .build();

        bannerBlockForNewsAndStocksService.updateBannerBlockForNewsAndStocks(bannerBlock);
        verify(bannerBlockForNewsAndStocksRepository, times(1)).save(bannerBlock);
    }
}