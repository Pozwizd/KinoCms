package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.page.Page;
import spacelab.kinocms.repository.PageRepository;
import spacelab.kinocms.service.ImagePageService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PageServiceImpTest {

    @Mock
    private PageRepository pageRepository;

    @Mock
    private ImagePageService imagePageService;

    @Mock
    private UploadFile uploadFile;

    @InjectMocks
    private PageServiceImp pageService;

    @BeforeEach
    public void setUp() {
        reset(pageRepository);
    }

    @Test
    void testSavePage() {
        Page page = new Page();
        pageService.savePage(page);
        verify(pageRepository).save(page);
    }

    @Test
    void testGetPage() {
        long id = 1L;
        Page expectedPage = new Page();
        when(pageRepository.findById(id)).thenReturn(Optional.of(expectedPage));
        Page result = pageService.getPage(id);
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetBasicPages() {
        List<Page> allPages = Arrays.asList(new Page(1L),
                new Page(2L),
                new Page(3L),
                new Page(4L),
                new Page(5L),
                new Page(6L));
        List<Page> expectedBasicPages = allPages.subList(0, 5);
        when(pageRepository.findAll()).thenReturn(allPages);
        List<Page> result = pageService.getBasicPages();
        assertEquals(expectedBasicPages, result);
    }

    @Test
    void testGetNewPages() {

        List<Page> allPages = Arrays.asList(new Page(1L),
                new Page(2L),
                new Page(3L),
                new Page(4L),
                new Page(5L),
                new Page(6L));
        List<Page> expectedNewPages = allPages.subList(5, allPages.size());
        when(pageRepository.findAll()).thenReturn(allPages);
        List<Page> result = pageService.getNewPages();
        assertEquals(expectedNewPages, result);
    }

    @Test
    void testGetAllPages() {
        List<Page> expectedPages = Arrays.asList(new Page(), new Page(), new Page());
        when(pageRepository.findAll()).thenReturn(expectedPages);
        List<Page> result = pageService.getAllPages();
        assertEquals(expectedPages, result);
    }

    @Test
    void testDeletePage() {
        Page page = new Page();
        pageService.deletePage(page);
        verify(pageRepository).delete(page);
    }

    @Test
    void testUpdatePage() {
        Page page = new Page();
        pageService.updatePage(page);
        verify(pageRepository).save(page);
    }


    @Test
    public void test_updatePageWithProvidedData() {
        Long pageId = 1L;
        Page existingPage = new Page();
        existingPage.setId(pageId);
        existingPage.setMainImage("existing-image.jpg");
        Page updatedPage = new Page();
        updatedPage.setId(pageId);
        updatedPage.setMainImage("new-image.jpg");
        when(pageRepository.findById(pageId)).thenReturn(Optional.of(existingPage));
        pageService.editPage(updatedPage);
        ArgumentCaptor<Page> pageCaptor = ArgumentCaptor.forClass(Page.class);
        verify(pageRepository, times(1)).save(pageCaptor.capture());
        Page savedPage = pageCaptor.getValue();
        assertEquals("new-image.jpg", savedPage.getMainImage());
        assertEquals(existingPage.getDateOfCreated(), savedPage.getDateOfCreated());
    }
}