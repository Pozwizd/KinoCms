package spacelab.kinocms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/distribution")
public class DistributionController {

    @GetMapping({"/", ""})
    public ModelAndView index(Model model){
        model.addAttribute("title", "Рассылка");
        model.addAttribute("pageActive", "distribution");

        return new ModelAndView("admin/distribution");
    }
}
