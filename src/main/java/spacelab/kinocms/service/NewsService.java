package spacelab.kinocms.service;

import spacelab.kinocms.entity.News;

public interface NewsService {

    Iterable<News> listAllNews();

    void saveNews(News news);

    News getNews(long id);

    News getLastNews();

    void deleteNews(long id);

    void updateNews(News news);

    Long idLastFilm();
}
