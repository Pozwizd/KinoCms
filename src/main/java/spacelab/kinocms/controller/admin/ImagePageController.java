package spacelab.kinocms.controller.admin;

import org.springframework.web.bind.annotation.RestController;
import spacelab.kinocms.service.ImagePageService;
import spacelab.kinocms.service.PageService;

import java.nio.file.Paths;

@RestController
public class ImagePageController {
    private static final String UPLOAD_FOLDER = Paths.get("images").toFile().getAbsolutePath() + "/";

    private final PageService pageService;
    private final ImagePageService imagePageService;

    public ImagePageController(PageService pageService, ImagePageService imagePageService) {
        this.pageService = pageService;
        this.imagePageService = imagePageService;
    }


}
