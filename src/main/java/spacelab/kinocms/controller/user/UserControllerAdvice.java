package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import spacelab.kinocms.repository.MainPageRepository;
import spacelab.kinocms.service.*;

@ControllerAdvice(basePackages = "spacelab.kinocms.controller.user")
@AllArgsConstructor
public class UserControllerAdvice {

    private final BannerBlockService bannerBlockService;
    private final BannerBackgroundService bannerBackgroundService;
    private final MainPageService mainPageService;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("mainPage", mainPageService.getMainPage());
        model.addAttribute("bannerBlocks", bannerBlockService.getBannerBlock(1L));
        model.addAttribute("bannerBackground", bannerBackgroundService.getBannerBackground(1L));


    }

}