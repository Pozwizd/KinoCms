package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.entity.banners.Banner;
import spacelab.kinocms.entity.banners.BannerBlock;
import spacelab.kinocms.repository.BannerBlockRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BannerBlockServiceImpTest {

    @Mock
    private BannerBlockRepository bannerBlockRepository;

    @InjectMocks
    private BannerBlockServiceImp bannerBlockService;

    @BeforeEach
    public void setUp() {
        reset(bannerBlockRepository);
    }

    @Test
    public void testSaveBannerBlock() {
        BannerBlock bannerBlock = new BannerBlock();
        bannerBlock.setId(1L);
        bannerBlock.setStatus(true);
        bannerBlock.setBanners(List.of(new Banner()));

        bannerBlockService.saveBannerBlock(bannerBlock);
        verify(bannerBlockRepository, times(1)).save(bannerBlock);
    }

    @Test
    public void testDeleteBannerBlock() {
        bannerBlockService.deleteBannerBlock(1L);
        verify(bannerBlockRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetBannerBlock() {
        BannerBlock bannerBlock = new BannerBlock();
        bannerBlock.setId(1L);
        bannerBlock.setStatus(true);
        bannerBlock.setBanners(List.of(new Banner()));

        when(bannerBlockRepository.findById(1L)).thenReturn(Optional.of(bannerBlock));

        BannerBlock result = bannerBlockService.getBannerBlock(1L);
        assertNotNull(result);
        assertEquals(bannerBlock, result);
    }

    @Test
    public void testUpdateBannerBlock() {
        BannerBlock bannerBlock = new BannerBlock();
        bannerBlock.setId(1L);
        bannerBlock.setStatus(true);
        bannerBlock.setBanners(List.of(new Banner()));

        bannerBlockService.updateBannerBlock(bannerBlock);
        verify(bannerBlockRepository, times(1)).save(bannerBlock);
    }
}