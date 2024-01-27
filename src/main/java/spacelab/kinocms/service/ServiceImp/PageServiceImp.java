package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PageServiceImp implements PageService {

    private static final String UPLOAD_FOLDER = Paths.get("images").toFile().getAbsolutePath() + "/";;
    private final PageRepository pageRepository;
    private final ImagePageService imagePageService;

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
    public void editPage(Page page, MultipartFile mainImagePage) {

        if (mainImagePage.getOriginalFilename() != null && mainImagePage.getOriginalFilename() != "" && mainImagePage.getSize() > 0){
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
        } else {
            page.setMainImage(pageRepository.findById(page.getId()).get().getMainImage());
        }

        page.setDateOfCreated(pageRepository.findById(page.getId()).get().getDateOfCreated());
        pageRepository.save(page);
    }

}
