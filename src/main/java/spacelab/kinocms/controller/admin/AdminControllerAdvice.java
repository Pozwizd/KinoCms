package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public void addCommonAttributes(Model model, Principal principal, HttpServletRequest request) {
        userRepository.findUserByEmail(principal.getName()).ifPresent(user
                -> model.addAttribute("user", user));
    }
}
