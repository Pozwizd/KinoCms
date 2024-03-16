package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.News;
import spacelab.kinocms.repository.NewsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceImpTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsServiceImp newsService;


    @BeforeEach
    public void setUp() {
        reset(newsRepository);
    }

    @Test
    void testListAllNews() {
        List<News> expectedNews = Arrays.asList(new News(), new News(), new News());
        when(newsRepository.findAll()).thenReturn(expectedNews);
        Iterable<News> result = newsService.listAllNews();
        assertEquals(expectedNews, result);
    }

    @Test
    void testSaveNews() {
        News news = new News();
        newsService.saveNews(news);
        verify(newsRepository).save(news);
    }

    @Test
    void testGetNews() {
        long id = 1L;
        News expectedNews = new News();
        when(newsRepository.findById(id)).thenReturn(Optional.of(expectedNews));
        News result = newsService.getNews(id);
        assertEquals(expectedNews, result);
    }

    @Test
    void testGetLastNews() {
        List<News> allNews = Arrays.asList(new News(), new News(), new News());
        News expectedNews = allNews.get(allNews.size() - 1);
        when(newsRepository.findAll()).thenReturn(allNews);
        News result = newsService.getLastNews();
        assertEquals(expectedNews, result);
    }

    @Test
    void testDeleteNews() {
        long id = 1L;
        newsService.deleteNews(id);
        verify(newsRepository).deleteById(id);
    }

    @Test
    void testUpdateNews() {
        News news = new News();
        newsService.updateNews(news);
        verify(newsRepository).save(news);
    }
}