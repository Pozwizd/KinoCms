package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.News;
import spacelab.kinocms.repository.NewsRepository;
import spacelab.kinocms.service.NewsService;

@Service
@AllArgsConstructor
public class NewsServiceImp implements NewsService {

    private final NewsRepository newsRepository;
    private static final Logger logger = LogManager.getLogger(NewsServiceImp.class);


    @Override
    public Iterable<News> listAllNews() {
        logger.info("Get all news");
        return newsRepository.findAll();
    }

    @Override
    public void saveNews(News news) {
        logger.info("Save news: {}", news);
        newsRepository.save(news);
    }

    @Override
    public News getNews(long id) {
        logger.info("Get news by id: {}", id);
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public News getLastNews() {
        logger.info("Get last news");
        return newsRepository.findAll().get(newsRepository.findAll().size() - 1);
    }

    @Override
    public void deleteNews(long id) {
        logger.info("Delete news by id: {}", id);
        newsRepository.deleteById(id);
    }

    @Override
    public void updateNews(News news) {
        logger.info("Update news: {}", news);
        news.setDateCreated(newsRepository.findById(news.getId()).get().getDateCreated());
        newsRepository.save(news);
    }
}
