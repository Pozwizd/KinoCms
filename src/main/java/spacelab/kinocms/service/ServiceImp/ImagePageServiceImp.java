package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.page.ImagePage;
import spacelab.kinocms.entity.page.Page;
import spacelab.kinocms.repository.ImagePageRepository;
import spacelab.kinocms.service.ImagePageService;

import java.util.List;

@Service
@AllArgsConstructor
public class ImagePageServiceImp implements ImagePageService {

    private final ImagePageRepository imagePageRepository;
    private static final Logger logger = LogManager.getLogger(ImagePageServiceImp.class);

    @Override
    public void saveImagePage(ImagePage imagePage) {
        logger.info("Save image page: " + imagePage);
        imagePageRepository.save(imagePage);
    }

    @Override
    public ImagePage getImagePage(long id) {
        logger.info("Get image page by id: " + id);
        return imagePageRepository.findById(id).orElse(null);
    }

    @Override
    public ImagePage getLastImagePage() {
        logger.info("Get last image page");
        return imagePageRepository.findAll().get(imagePageRepository.findAll().size() - 1);
    }

    @Override
    public List<ImagePage> getAllImagesPage() {
        logger.info("Get all image page");
        return imagePageRepository.findAll();
    }

    @Override
    public List<ImagePage> getAllImagesPageByPage(Page page) {
        logger.info("Get all image page by page: " + page);
        return imagePageRepository.findAllByPage(page);
    }
    @Override
    public void deleteImagePage(long id) {
        logger.info("Delete image page by id: " + id);
        imagePageRepository.deleteById(id);
    }

    @Override
    public void updateImagePage(ImagePage imagePage) {
        logger.info("Update image page: " + imagePage);
        imagePageRepository.save(imagePage);
    }

    @Override
    public void deleteImagePageByPage(Page page) {
        logger.info("Delete image page by page: " + page);
        List<ImagePage> imagePages = imagePageRepository.findAllByPage(page);
        imagePageRepository.deleteAll(imagePages);
    }
}
