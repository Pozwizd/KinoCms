package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import spacelab.kinocms.model.page.Page;
import spacelab.kinocms.repository.MainPageRepository;
import spacelab.kinocms.service.*;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "spacelab.kinocms.controller.user")
@AllArgsConstructor
public class UserControllerAdvice {

    private final BannerBlockService bannerBlockService;
    private final BannerBackgroundService bannerBackgroundService;
    private final MainPageService mainPageService;
    private final PageService pageService;
    private final NewsService newsService;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("mainPage", mainPageService.getMainPage());
        model.addAttribute("bannerBlocks", bannerBlockService.getBannerBlock(1L));
        model.addAttribute("bannerBackground", bannerBackgroundService.getBannerBackground(1L));

        model.addAttribute("basicPages", newsService);

        model.addAttribute("pages", pageService.getAllPages());

    }

}