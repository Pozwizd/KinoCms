package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Images.ImageNewsDto;
import spacelab.kinocms.Dto.NewsDto;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.ImagesEntity.ImageNews;
import spacelab.kinocms.entity.News;
import spacelab.kinocms.service.ImageNewsService;
import spacelab.kinocms.service.NewsService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NewsMapper {
    private final NewsService newsService;
    private final ImageNewsService imageNewsService;
    private final UploadFile uploadFile;

    public NewsMapper(NewsService newsService, ImageNewsService imageNewsService, UploadFile uploadFile) {
        this.newsService = newsService;
        this.imageNewsService = imageNewsService;
        this.uploadFile = uploadFile;
    }

    public News toEntity(NewsDto newsDto) {

        News newsFromDb = newsService.getNews(newsDto.getId());
        if (newsFromDb.getId() == null){
            newsFromDb.setDateCreated(new Date(System.currentTimeMillis()));
        }
        newsFromDb.setName(newsDto.getName());
        newsFromDb.setDatePosting(Date.valueOf(newsDto.getDatePosting()));
        newsFromDb.setDescription(newsDto.getDescription());
        newsFromDb.setLinkVideo(newsDto.getLinkVideo());
        newsFromDb.setSeoUrl(newsDto.getSeoUrl());
        newsFromDb.setSeoTitle(newsDto.getSeoTitle());
        newsFromDb.setSeoKeywords(newsDto.getSeoKeywords());
        newsFromDb.setSeoDescription(newsDto.getSeoDescription());


        newsService.saveNews(newsFromDb);

        if (newsDto.getImagesNews() != null) {
            List<Long> ids = newsDto
                    .getImagesNews().stream().map(ImageNewsDto::getId).filter(Objects::nonNull).toList();

            newsFromDb.getImagesNews().stream().filter(imageNews -> !ids.contains(imageNews.getId()))
                    .forEach(imageNewsService::deleteImageNews);
            newsFromDb.getImagesNews()
                    .removeIf(imageFilm -> !ids.contains(imageFilm.getId()));
        }

        if (newsDto.getMainImage().getSize() != 0) {
            newsFromDb.setMainImage(uploadFile.uploadFile(newsDto.getMainImage(), newsFromDb.getMainImage()));
        }

        if (newsDto.getImagesNews() != null) {
            for (ImageNewsDto imageNewsDto : newsDto.getImagesNews()) {
                ImageNews imageNews = imageNewsDto.getId() == null ? new ImageNews() :  imageNewsService.getImageNews(imageNewsDto.getId());
                imageNews.setId(imageNewsDto.getId());
                if (!imageNewsDto.getUrl().isEmpty()) {
                    imageNews.setUrl(uploadFile.uploadFile(imageNewsDto.getUrl(), imageNews.getUrl()));
                }
                imageNews.setNews(newsFromDb);
                imageNewsService.updateImageNews(imageNews);
                newsFromDb.getImagesNews().add(imageNews);
            }
        } else {
            if (newsFromDb.getImagesNews() != null) {
                for (ImageNews imageNews : newsFromDb.getImagesNews()) {
                    imageNewsService.deleteImageNews(imageNews);
                }
                newsFromDb.setImagesNews(new ArrayList<>());
            }
        }
        return newsFromDb;
    }
}
