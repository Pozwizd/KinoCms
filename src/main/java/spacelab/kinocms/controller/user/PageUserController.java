package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.service.ContactCinemaService;
import spacelab.kinocms.service.PageService;

@Controller
@RequestMapping("/pages")
@AllArgsConstructor
public class PageUserController {

    private final PageService pageService;
    private final ContactCinemaService contactCinemaService;

    @GetMapping("/{id}")
    public ModelAndView index(@PathVariable String id, Model model) {

        model.addAttribute("title", pageService.getPage(Long.parseLong(id)).getName());
        model.addAttribute("page", pageService.getPage(Long.parseLong(id)));
        return new ModelAndView("user/pages/page");
    }

    @GetMapping("/contact")
    public ModelAndView getContact(Model model) {

        model.addAttribute("title", "Контакты");
        model.addAttribute("contactsCinemas", contactCinemaService.getContactCinemas());
        return new ModelAndView("user/pages/contactPage");
    }
}
