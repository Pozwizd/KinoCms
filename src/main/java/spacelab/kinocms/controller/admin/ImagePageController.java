package spacelab.kinocms.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.service.ImagePageService;
import spacelab.kinocms.service.PageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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
