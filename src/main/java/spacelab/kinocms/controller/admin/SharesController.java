package spacelab.kinocms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/shares")
public class SharesController {

    @GetMapping({"/", ""})
    public ModelAndView index(Model model){
        model.addAttribute("title", "Акции");
        model.addAttribute("pageActive", "shares");
        return new ModelAndView("admin/shares");
    }
}
