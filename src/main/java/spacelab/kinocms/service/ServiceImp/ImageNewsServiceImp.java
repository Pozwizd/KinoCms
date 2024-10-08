package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;
import spacelab.kinocms.repository.ImageNewsRepository;
import spacelab.kinocms.service.ImageNewsService;
import spacelab.kinocms.service.NewsService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageNewsServiceImp implements ImageNewsService {

    private final ImageNewsRepository imageNewsRepository;
    private final NewsService newsService;
    private static final Logger logger = LogManager.getLogger(ImageNewsServiceImp.class);


    @Override
    public void saveImageNews(ImageNews imageNews) {
        imageNewsRepository.save(imageNews);
        logger.info("Save image news: " + imageNews);
    }

    @Override
    public ImageNews getImageNews(long id) {
        logger.info("Get image news by id: " + id);
        return imageNewsRepository.findById(id).orElse(new ImageNews());
    }

    @Override
    public ImageNews getLastImageNews(String id) {

        logger.info("Get last image news");
        return imageNewsRepository.findTopImageNewsByNewsOrderByIdDesc(newsService.getNews(Long.parseLong(id)));
    }

    @Override
    public List<ImageNews> getAllImagesNews() {
        logger.info("Get all image news");
        return imageNewsRepository.findAll();
    }

    @Override
    public List<ImageNews> getAllImagesNewsByNews(News news) {
        logger.info("Get all image news by news: " + news);
        return imageNewsRepository.findAllByNews(news);
    }

    @Override
    public void deleteImageNews(ImageNews imageNews) {
        logger.info("Delete image news: {}", imageNews);
        imageNewsRepository.delete(imageNews);
    }

    @Override
    public void updateImageNews(ImageNews imageNews) {
        logger.info("Update image news: " + imageNews);
        imageNewsRepository.save(imageNews);
    }

}
