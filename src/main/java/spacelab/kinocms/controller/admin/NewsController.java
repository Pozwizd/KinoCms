package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.NewsDto;
import spacelab.kinocms.Mapper.NewsMapper;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.News;
import spacelab.kinocms.service.ImageNewsService;
import spacelab.kinocms.service.NewsService;

@Controller
@RequestMapping("admin/news")
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final UploadFile uploadFile;
    private final ImageNewsService imageNewsService;
    private final NewsMapper newsMapper;



    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Новости");
        model.addAttribute("pageActive", "news");

        model.addAttribute("newsList", newsService.listAllNews());

        return new ModelAndView("admin/news/news");
    }

    @GetMapping("/editNews/{id}")
    public ModelAndView showEditMainPage(Model model, @PathVariable String id) {
        News news = newsService.getNews(Long.parseLong(id));
        model.addAttribute("title", "Редактирование новости " + news.getName());
        model.addAttribute("pageActive", "News");

        model.addAttribute("news", news);
        return new ModelAndView("admin/news/editNews");
    }

    @PostMapping("/editNews/{id}")
    public ModelAndView editBasicPage(@Valid @ModelAttribute("news") NewsDto newsDto,
                                      BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Редактирование фильма " + newsDto.getName());
            model.addAttribute("pageActive", "film");
            model.addAttribute("film", newsService.getNews(newsDto.getId()));
            return new ModelAndView("admin/news/editNews");
        }
        if(newsDto.getImagesNews() != null) {
            newsDto.getImagesNews().removeIf(imageNewsDto -> imageNewsDto.getId() == null
                    && imageNewsDto.getUrl().getSize() == 0);
            if(newsDto
                    .getImagesNews()
                    .stream()
                    .anyMatch(image -> !uploadFile
                            .isAllowedImageTypeAndSize(image.getUrl()))){
                model.addAttribute("title", "Редактирование новости " + newsDto.getName());
                model.addAttribute("pageActive", "news");
                model.addAttribute("news", newsService.getNews(newsDto.getId()));
                return new ModelAndView("admin/news/editNews");
            }

        }
        newsService.updateNews(newsMapper.toEntity(newsDto));
        return new ModelAndView("redirect:/admin/news");
    }

    @GetMapping("/createNews")
    public ModelAndView createNewPage(Model model, HttpServletRequest request) {
        News news = new News();
        news.setId(newsService.idLastFilm() + 1);
        news.setName("Новый новость");
        news.setMainImage(null);

        return new ModelAndView("admin/news/editNews", "news", news);
    }

    @GetMapping("/removeNews/{id}")
    public ModelAndView removePage(Model model, @PathVariable String id, HttpServletRequest request) {
        newsService.deleteNews(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

}

