package spacelab.kinocms.service.ServiceImp;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.model.page.Page;
import spacelab.kinocms.repository.ImagePageRepository;
import spacelab.kinocms.service.ImagePageService;

import java.util.List;

@Service
public class ImagePageServiceImp implements ImagePageService {

    private final ImagePageRepository imagePageRepository;

    public ImagePageServiceImp(ImagePageRepository imagePageRepository) {
        this.imagePageRepository = imagePageRepository;
    }

    @Override
    public void saveImagePage(ImagePage imagePage) {
        imagePageRepository.save(imagePage);
    }

    @Override
    public ImagePage getImagePage(long id) {
        return imagePageRepository.findById(id).orElse(null);
    }

    @Override
    public ImagePage getLastImagePage() {

        return imagePageRepository.findAll().get(imagePageRepository.findAll().size() - 1);
    }

    @Override
    public List<ImagePage> getAllImagesPage() {
        return imagePageRepository.findAll();
    }

    @Override
    public List<ImagePage> getAllImagesPageByPage(Page page) {
        return imagePageRepository.findAllByPage(page);
    }
    @Override
    public void deleteImagePage(long id) {
        imagePageRepository.deleteById(id);
    }

    @Override
    public void updateImagePage(ImagePage imagePage) {
        imagePageRepository.save(imagePage);
    }

    @Override
    public void deleteImagePageByPage(Page page) {
        List<ImagePage> imagePages = imagePageRepository.findAllByPage(page);
        imagePageRepository.deleteAll(imagePages);
    }
}
