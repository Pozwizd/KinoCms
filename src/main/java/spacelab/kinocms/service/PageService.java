package spacelab.kinocms.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.model.page.Page;

import java.util.List;

@Service
public interface PageService {

    public void savePage(Page page);
    public Page getPage(long id);

    public List<Page> getBasicPages();

    public List<Page> getNewPages();


    public List<Page> getAllPages();

    public void deletePage(Page page);
    public void updatePage(Page page);

    void editPage(Page page);
}
