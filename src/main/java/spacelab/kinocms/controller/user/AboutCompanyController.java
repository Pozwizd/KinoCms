package spacelab.kinocms.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutCompanyController {

    @GetMapping("/aboutCinema")
    public ModelAndView getAboutCinema(Model model) {
        model.addAttribute("title", "О кинотеатре");
        return new ModelAndView("user/aboutCinema/aboutCinema");
    }

    @GetMapping("/app")
    public ModelAndView getApp(Model model) {
        model.addAttribute("title","Мобильное приложение");
        return new ModelAndView("user/aboutCinema/app");
    }



}
