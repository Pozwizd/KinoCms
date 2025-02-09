package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.entity.page.ImagePage;
import spacelab.kinocms.entity.page.Page;
import spacelab.kinocms.repository.ImagePageRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImagePageServiceImpTest {

    @Mock
    private ImagePageRepository imagePageRepository;

    @InjectMocks
    private ImagePageServiceImp imagePageService;

    @BeforeEach
    public void setUp() {
        reset(imagePageRepository);
    }

    @Test
    void saveImagePageTest() {
        ImagePage imagePage = new ImagePage();
        imagePageService.saveImagePage(imagePage);
        verify(imagePageRepository, times(1)).save(imagePage);
    }

    @Test
    void getImagePageTest() {
        long id = 1L;
        ImagePage expectedImagePage = new ImagePage();
        when(imagePageRepository.findById(id)).thenReturn(Optional.of(expectedImagePage));
        ImagePage actualImagePage = imagePageService.getImagePage(id);
        assertEquals(expectedImagePage, actualImagePage);
    }



    @Test
    void getLastImagePageTest() {
        List<ImagePage> imagePages = Arrays.asList(new ImagePage(), new ImagePage(), new ImagePage());
        when(imagePageRepository.findAll()).thenReturn(imagePages);
        ImagePage expectedImagePage = imagePages.get(imagePages.size() - 1);
        ImagePage actualImagePage = imagePageService.getLastImagePage();
        assertEquals(expectedImagePage, actualImagePage);
    }

    @Test
    void getAllImagesPageTest() {
        List<ImagePage> expectedImagePages = Arrays.asList(new ImagePage(), new ImagePage());
        when(imagePageRepository.findAll()).thenReturn(expectedImagePages);
        List<ImagePage> actualImagePages = imagePageService.getAllImagesPage();
        assertEquals(expectedImagePages, actualImagePages);
    }

    @Test
    void getAllImagesPageByPageTest() {
        Page page = new Page();
        List<ImagePage> expectedImagePages = Arrays.asList(new ImagePage(), new ImagePage());
        when(imagePageRepository.findAllByPage(page)).thenReturn(expectedImagePages);
        List<ImagePage> actualImagePages = imagePageService.getAllImagesPageByPage(page);
        assertEquals(expectedImagePages, actualImagePages);
    }

    @Test
    void deleteImagePageTest() {
        long id = 1L;
        imagePageService.deleteImagePage(id);
        verify(imagePageRepository, times(1)).deleteById(id);
    }

    @Test
    void updateImagePageTest() {
        ImagePage imagePage = new ImagePage();
        imagePageService.updateImagePage(imagePage);
        verify(imagePageRepository, times(1)).save(imagePage);
    }

    @Test
    void deleteImagePageByPageTest() {
        Page page = new Page();
        List<ImagePage> imagePages = Arrays.asList(new ImagePage(), new ImagePage());
        when(imagePageRepository.findAllByPage(page)).thenReturn(imagePages);
        imagePageService.deleteImagePageByPage(page);
        verify(imagePageRepository, times(1)).deleteAll(imagePages);
    }
}