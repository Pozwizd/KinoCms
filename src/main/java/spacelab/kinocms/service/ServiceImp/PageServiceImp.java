package spacelab.kinocms.service.ServiceImp;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.model.page.Page;
import spacelab.kinocms.repository.PageRepository;
import spacelab.kinocms.service.ImagePageService;
import spacelab.kinocms.service.PageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImp implements PageService {

    private static final String UPLOAD_FOLDER = Paths.get("images").toFile().getAbsolutePath() + "/";;
    private final PageRepository pageRepository;
    private final ImagePageService imagePageService;

    public PageServiceImp(PageRepository pageRepository, ImagePageService imagePageService) {
        this.pageRepository = pageRepository;
        this.imagePageService = imagePageService;
    }

    @Override
    public void savePage(Page page) {
        pageRepository.save(page);
    }

    @Override
    public Page getPage(long id) {
        return pageRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Page not found"));
    }

    @Override
    public List<Page> getBasicPages() {
        return pageRepository.findAll().stream().filter(page -> page.getId() < 6).toList();
    }

    @Override
    public List<Page> getNewPages() {
        return pageRepository.findAll().stream().filter(page -> page.getId() >= 6).toList();
    }

    @Override
    public void deletePage(Page page) {
        pageRepository.delete(page);
    }

    @Override
    public void updatePage(Page page) {
        pageRepository.save(page);
    }

    @Override
    public void editPage(Page page, MultipartFile mainImagePage, List<MultipartFile> imagesAboutCinema) {
        if (mainImagePage != null){
            String filePath = Paths.get("").toFile().getAbsolutePath() + pageRepository.findById(page.getId()).get().getMainImage();
            File file = new File(filePath);
            file.delete();
            String fileName = mainImagePage.getOriginalFilename();
            try {
                mainImagePage.transferTo(new File(UPLOAD_FOLDER + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            page.setMainImage("/images/" + fileName);
        }

        for(ImagePage imagePage : page.getImagesAboutCinema()){
            String filePath = Paths.get("").toFile().getAbsolutePath() + imagePage.getUrl();
            File file = new File(filePath);
            file.delete();
        }

        imagePageService.deleteImagePageByPage(page);
        page.getImagesAboutCinema().clear();
        if (imagesAboutCinema != null){
            for(MultipartFile image : imagesAboutCinema){
                String fileName = image.getOriginalFilename();
                try {
                    image.transferTo(new File(UPLOAD_FOLDER + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImagePage imagePage = new ImagePage();
                imagePage.setPage(page);
                imagePage.setUrl("/images/" + fileName);
                imagePageService.saveImagePage(imagePage);
                page.getImagesAboutCinema().add(imagePage);
            }
        }



        pageRepository.save(page);
    }

}
