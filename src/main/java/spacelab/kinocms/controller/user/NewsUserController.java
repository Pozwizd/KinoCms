package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.service.NewsService;

@Controller
@RequestMapping("/news")
@AllArgsConstructor
public class NewsUserController {

    private final NewsService newsService;

    @GetMapping({"/", ""})
    public ModelAndView news(Model model) {
        model.addAttribute("title", "Новости");
        model.addAttribute("news", newsService.listAllNews());
        return new ModelAndView("user/news/news");
    }

    @GetMapping("/{id}")
    public ModelAndView getNews(Model model, @PathVariable String id) {
        model.addAttribute("title", newsService.getNews(Long.parseLong(id)).getName());
        model.addAttribute("news", newsService.getNews(Long.parseLong(id)));

        return new ModelAndView("user/news/newsCard");
    }
}

