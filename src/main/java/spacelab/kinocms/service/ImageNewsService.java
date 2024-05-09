package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;

import java.util.List;

@Service
public interface ImageNewsService {

    void saveImageNews(ImageNews imageNews);

    ImageNews getImageNews(long id);

    ImageNews getLastImageNews(String id);

    List<ImageNews> getAllImagesNews();

    List<ImageNews> getAllImagesNewsByNews(News news);

    void deleteImageNews(ImageNews imageNews);

    void updateImageNews(ImageNews imageNews);

}
