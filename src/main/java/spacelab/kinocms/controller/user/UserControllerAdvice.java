package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import spacelab.kinocms.repository.UserRepository;
import spacelab.kinocms.service.*;

import java.security.Principal;

@ControllerAdvice(basePackages = "spacelab.kinocms.controller.user")
@AllArgsConstructor
public class UserControllerAdvice {

    private final BannerBlockService bannerBlockService;
    private final BannerBackgroundService bannerBackgroundService;
    private final MainPageService mainPageService;
    private final PageService pageService;
    private final NewsService newsService;
    private final UserRepository userRepository;

    @ModelAttribute
    public void addCommonAttributes(Model model, Principal principal) {
        model.addAttribute("mainPage", mainPageService.getMainPage());
        model.addAttribute("bannerBlocks", bannerBlockService.getBannerBlock(1L));
        model.addAttribute("bannerBackground", bannerBackgroundService.getBannerBackground(1L));
        if (principal != null) {
            model.addAttribute("isUser", true);
            model.addAttribute("userName", userRepository
                    .findUserByEmail(principal.getName()).get().getName());
            model.addAttribute("userRole", userRepository
                    .findUserByEmail(principal.getName()).get().getRole().name());
        } else {
            model.addAttribute("isUser", false);
        }


        model.addAttribute("basicPages", newsService);

        model.addAttribute("pages", pageService.getAllPages());

        if (principal != null) {
            String email = principal.getName();
            System.out.println(email);
        } else {
            System.out.println("No principal");
        }
    }
}
