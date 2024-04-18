package spacelab.kinocms.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.User;
import spacelab.kinocms.repository.UserRepository;
import spacelab.kinocms.service.UserService;

@Controller
@AllArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/authenticated")
    public ModelAndView getRedirectForAuthenticatedUser() {

        if(!isUserAuthenticated()) {
            return new ModelAndView("redirect:/login");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return new ModelAndView("redirect:/admin");
        }


        User user = userRepository.findUserByEmail(auth.getName()).orElse(null);
        if(auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("USER"))) {
            return new ModelAndView("redirect:/cabinet/"+ (user != null ? user.getId() : null));
        }

        return new ModelAndView("redirect:/" + auth);
    }

    private boolean isUserAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

}
