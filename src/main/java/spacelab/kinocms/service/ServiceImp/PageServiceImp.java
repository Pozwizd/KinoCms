package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.page.Page;
import spacelab.kinocms.repository.PageRepository;
import spacelab.kinocms.service.ImagePageService;
import spacelab.kinocms.service.PageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class PageServiceImp implements PageService {


    private final PageRepository pageRepository;
    private final ImagePageService imagePageService;
    private final UploadFile uploadFile;
    private static final Logger logger = LogManager.getLogger(PageServiceImp.class);

    @Override
    public void savePage(Page page) {
        logger.info("Save page: " + page);
        pageRepository.save(page);
    }

    @Override
    public Page getPage(long id) {
        logger.info("Get page by id: " + id);
        return pageRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Page not found"));
    }

    @Override
    public List<Page> getBasicPages() {
        logger.info("Get basic pages");
        return pageRepository.findAll().stream().filter(page -> page.getId() < 6).toList();
    }

    @Override
    public List<Page> getNewPages() {
        logger.info("Get new pages");
        return pageRepository.findAll().stream().filter(page -> page.getId() >= 6).toList();
    }

    @Override
    public List<Page> getAllPages() {
        logger.info("Get all pages");
        return pageRepository.findAll();
    }

    @Override
    public void deletePage(Page page) {
        logger.info("Delete page: " + page);
        pageRepository.delete(page);
    }

    @Override
    public void updatePage(Page page) {
        logger.info("Update page: " + page);
        pageRepository.save(page);
    }

    @Override
    public void editPage(Page page, MultipartFile mainImagePage) {

        if (mainImagePage.getOriginalFilename() != null && mainImagePage.getOriginalFilename() != "" && mainImagePage.getSize() > 0){
            page.setMainImage(uploadFile.uploadFile(mainImagePage,
                    pageRepository.findById(page.getId()).get().getMainImage()));
        } else {
            page.setMainImage(pageRepository.findById(page.getId()).get().getMainImage());
        }

        page.setDateOfCreated(pageRepository.findById(page.getId()).get().getDateOfCreated());

        logger.info("Update page: " + page);
        pageRepository.save(page);
    }

}
