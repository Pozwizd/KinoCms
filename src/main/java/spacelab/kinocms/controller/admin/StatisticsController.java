package spacelab.kinocms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/admin")
public class StatisticsController {


    @GetMapping({"/", ""})
    public ModelAndView statistics(Model model) {
        model.addAttribute("title", "Statistics");
        model.addAttribute("pageActive", "statistics");

        return new ModelAndView("admin/statistics");
    }

}
