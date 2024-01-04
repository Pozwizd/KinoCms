package spacelab.kinocms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/news")
public class NewsController {

    @GetMapping({"/", ""})
    public ModelAndView index (Model model){
        model.addAttribute("title", "Новости");
        model.addAttribute("pageActive", "news");

        return new ModelAndView("admin/news");
    }

}
