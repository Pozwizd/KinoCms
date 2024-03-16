package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;
import spacelab.kinocms.repository.ImageNewsRepository;
import spacelab.kinocms.service.ImageNewsService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageNewsServiceImp implements ImageNewsService {

    private final ImageNewsRepository imageNewsRepository;
    private static final Logger logger = LogManager.getLogger(ImageNewsServiceImp.class);


    @Override
    public void saveImageNews(ImageNews imageNews) {
        imageNewsRepository.save(imageNews);
        logger.info("Save image news: " + imageNews);
    }

    @Override
    public ImageNews getImageNews(long id) {
        logger.info("Get image news by id: " + id);
        return imageNewsRepository.findById(id).orElse(null);
    }

    @Override
    public ImageNews getLastImageNews() {
        logger.info("Get last image news");
        return imageNewsRepository.findAll().get(imageNewsRepository.findAll().size() - 1);
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
    public void deleteImageNews(long id) {
        logger.info("Delete image news by id: " + id);
        imageNewsRepository.deleteById(id);
    }

    @Override
    public void updateImageNews(ImageNews imageNews) {
        logger.info("Update image news: " + imageNews);
        imageNewsRepository.save(imageNews);
    }

}
