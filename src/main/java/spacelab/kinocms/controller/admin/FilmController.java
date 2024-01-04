package spacelab.kinocms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/films")
public class FilmController {

    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Фильмы");
        model.addAttribute("pageActive", "film");

        return new ModelAndView("admin/films");
    }
}
