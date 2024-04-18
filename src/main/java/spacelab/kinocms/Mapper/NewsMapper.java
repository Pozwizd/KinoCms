package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.NewsDto;
import spacelab.kinocms.model.News;
import spacelab.kinocms.service.NewsService;

import java.sql.Date;

@Service
public class NewsMapper {
    private final NewsService newsService;

    public NewsMapper(NewsService newsService) {
        this.newsService = newsService;
    }

    public News toEntity(NewsDto newsDto) {
        News news = newsService.getNews(newsDto.getId());
        if (news == null) {
            news = new News();
        }
        news.setId(newsDto.getId());
        news.setStatus(newsDto.getStatus());
        news.setName(newsDto.getName());
        news.setDatePosting(Date.valueOf(newsDto.getDatePosting()));
        news.setDescription(newsDto.getDescription());
        news.setLinkVideo(newsDto.getLinkVideo());
        news.setSeoUrl(newsDto.getSeoUrl());
        news.setSeoTitle(newsDto.getSeoTitle());
        news.setSeoKeywords(newsDto.getSeoKeywords());
        news.setSeoDescription(newsDto.getSeoDescription());

        return news;
    }

    public NewsDto toDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setStatus(news.getStatus());
        newsDto.setName(news.getName());
        newsDto.setDatePosting(news.getDatePosting().toLocalDate());
        newsDto.setDescription(news.getDescription());
        newsDto.setLinkVideo(news.getLinkVideo());
        newsDto.setSeoUrl(news.getSeoUrl());
        newsDto.setSeoTitle(news.getSeoTitle());
        newsDto.setSeoKeywords(news.getSeoKeywords());
        newsDto.setSeoDescription(news.getSeoDescription());
        return newsDto;
    }
}
