package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;
import spacelab.kinocms.repository.ImageNewsRepository;
import spacelab.kinocms.service.NewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageNewsServiceImpTest {

    @Mock
    private ImageNewsRepository imageNewsRepository;
    @Mock
    private NewsService newsService;
    @InjectMocks
    private ImageNewsServiceImp imageNewsService;

    @BeforeEach
    public void setUp() {
        reset(imageNewsRepository);
    }

    @Test
    public void saveImageNewsTest() {
        ImageNews imageNews = new ImageNews();
        imageNewsService.saveImageNews(imageNews);
        verify(imageNewsRepository).save(imageNews);
    }

    @Test
    public void getImageNewsTest() {
        long imageNewsId = 1L;
        ImageNews expectedImageNews = new ImageNews();
        when(imageNewsRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(expectedImageNews));
        ImageNews actualImageNews = imageNewsService.getImageNews(imageNewsId);
        assertEquals(expectedImageNews, actualImageNews);
    }

    @Test
    public void getLastImageNewsTest() {
//        Retest
        News news = new News();
        ImageNews imageNews = new ImageNews();
        ImageNews imageNews2 = new ImageNews();
        news.setId(1L);
        imageNews.setId(1L);
        imageNews2.setId(2L);
        imageNews.setNews(news);
        imageNews2.setNews(news);
        List<ImageNews> imageNewsList = new ArrayList<>();
        imageNewsList.add(imageNews);
        imageNewsList.add(imageNews2);
        when(newsService.getNews(1L)).thenReturn(news);
        when(imageNewsRepository.findTopImageNewsByNewsOrderByIdDesc(news)).thenReturn(imageNews2);
        ImageNews actualImageNews = imageNewsService.getLastImageNews("1");
        verify(imageNewsRepository).findTopImageNewsByNewsOrderByIdDesc(news);
        assertEquals(imageNews2, actualImageNews);


    }

    @Test
    public void getAllImagesNewsTest() {
        List<ImageNews> expectedImageNewsList = new ArrayList<>();
        when(imageNewsRepository.findAll()).thenReturn(expectedImageNewsList);
        List<ImageNews> actualImageNewsList = imageNewsService.getAllImagesNews();
        assertEquals(expectedImageNewsList, actualImageNewsList);
    }

    @Test
    public void getAllImagesNewsByNewsTest() {
        News news = new News();
        List<ImageNews> expectedImageNewsList = new ArrayList<>();
        when(imageNewsRepository.findAllByNews(Mockito.any(News.class))).thenReturn(expectedImageNewsList);
        List<ImageNews> actualImageNewsList = imageNewsService.getAllImagesNewsByNews(news);
        assertEquals(expectedImageNewsList, actualImageNewsList);
    }

    @Test
    public void deleteImageNewsTest() {
        long imageNewsId = 1L;
        ImageNews imageNews = new ImageNews();
        imageNews.setId(imageNewsId);
        imageNewsService.deleteImageNews(imageNews);
        verify(imageNewsRepository).delete(imageNews);
    }

    @Test
    public void updateImageNewsTest() {
        ImageNews imageNews = new ImageNews();
        imageNewsService.updateImageNews(imageNews);
        verify(imageNewsRepository).save(imageNews);
    }
}