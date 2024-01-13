package spacelab.kinocms.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.service.ImagePageService;
import spacelab.kinocms.service.PageService;

import java.util.List;

@Controller
public class ImagePageController {

    private final PageService pageService;
    private final ImagePageService imagePageService;

    public ImagePageController(PageService pageService, ImagePageService imagePageService) {
        this.pageService = pageService;
        this.imagePageService = imagePageService;
    }


    @GetMapping("/editPage/{id}/deleteMainImage")
    public ModelAndView deleteMainImage(@PathVariable String id) {
        ImagePage imagePage = new ImagePage();
        imagePageService.saveImagePage(imagePage);
        return new ModelAndView("redirect:admin/pages/editPage/" + id);
    }


}
