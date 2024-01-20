package spacelab.kinocms.service.ServiceImp;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.News;
import spacelab.kinocms.repository.NewsRepository;
import spacelab.kinocms.service.NewsService;

@Service
public class NewsServiceImp implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImp(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Iterable<News> listAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public void saveNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public News getNews(long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public News getLastNews() {
        return newsRepository.findAll().get(newsRepository.findAll().size() - 1);
    }

    @Override
    public void deleteNews(long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void updateNews(News news) {
        newsRepository.save(news);
    }
}
