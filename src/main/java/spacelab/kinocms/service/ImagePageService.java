package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.model.page.Page;

import java.util.List;

@Service
public interface ImagePageService {

    public void saveImagePage(ImagePage imagePage);
    public ImagePage getImagePage(long id);
    public ImagePage getLastImagePage();
    public List<ImagePage> getAllImagesPage();
    public List<ImagePage> getAllImagesPageByPage(Page page);
    public void deleteImagePage(long id);
    public void updateImagePage(ImagePage imagePage);
    public void deleteImagePageByPage(Page page);
}
