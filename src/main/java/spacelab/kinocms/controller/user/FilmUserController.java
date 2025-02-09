package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.service.BannerBackgroundService;
import spacelab.kinocms.service.BannerBlockService;
import spacelab.kinocms.service.BannerService;
import spacelab.kinocms.service.FilmService;

import java.util.Arrays;

import static java.util.Locale.filter;

@Controller
@AllArgsConstructor
@RequestMapping("/film/")
public class FilmUserController {

    private final BannerService bannerService;
    private final BannerBlockService bannerBlockService;
    private final FilmService filmService;
    private final BannerBackgroundService bannerBackgroundService;

    @GetMapping("{id}")
    public ModelAndView index(Model model, @PathVariable String id) {

        model.addAttribute("title", filmService.getFilm(Long.parseLong(id)).getName());
        model.addAttribute("banners", bannerService.getAllBanners());
        Film film = filmService.getFilm(Long.parseLong(id));
        String result = film.getLinkTrailer().substring(film.getLinkTrailer().lastIndexOf("/") + 1);
        film.setLinkTrailer(result);
        model.addAttribute("film", film);

        return new ModelAndView("user/cardFilm");
    }

}
