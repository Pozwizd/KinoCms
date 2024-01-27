package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
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


    @Override
    public void saveImageNews(ImageNews imageNews) {
        imageNewsRepository.save(imageNews);
    }

    @Override
    public ImageNews getImageNews(long id) {
        return imageNewsRepository.findById(id).orElse(null);
    }

    @Override
    public ImageNews getLastImageNews() {
        return imageNewsRepository.findAll().get(imageNewsRepository.findAll().size() - 1);
    }

    @Override
    public List<ImageNews> getAllImagesNews() {
        return imageNewsRepository.findAll();
    }

    @Override
    public List<ImageNews> getAllImagesNewsByNews(News news) {
        return imageNewsRepository.findAllByNews(news);
    }

    @Override
    public void deleteImageNews(long id) {
        imageNewsRepository.deleteById(id);
    }

    @Override
    public void updateImageNews(ImageNews imageNews) {
        imageNewsRepository.save(imageNews);
    }

}
