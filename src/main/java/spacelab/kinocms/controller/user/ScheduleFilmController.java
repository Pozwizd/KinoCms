package spacelab.kinocms.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScheduleFilmController {



    @GetMapping("/schedule")
    public ModelAndView index(Model model) {


        return new ModelAndView("user/schedule");
    }

    @GetMapping("/sorting")
    public ModelAndView sorting(Model model) {


        return new ModelAndView("user/posters");
    }
}
