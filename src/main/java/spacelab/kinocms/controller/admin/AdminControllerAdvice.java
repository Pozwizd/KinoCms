package spacelab.kinocms.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import spacelab.kinocms.model.User;
import spacelab.kinocms.repository.UserRepository;
import spacelab.kinocms.service.*;

import java.security.Principal;

@ControllerAdvice(basePackages = "spacelab.kinocms.controller.admin")
@AllArgsConstructor
public class AdminControllerAdvice {

    private final BannerBlockService bannerBlockService;
    private final BannerBackgroundService bannerBackgroundService;
    private final MainPageService mainPageService;
    private final PageService pageService;
    private final NewsService newsService;
    private final UserRepository userRepository;
    UserService userService;

    @ModelAttribute
    public void addCommonAttributes(Model model, Principal principal) {
        userRepository.findUserByEmail(principal.getName()).ifPresent(user
                -> model.addAttribute("user", user));
    }
}
