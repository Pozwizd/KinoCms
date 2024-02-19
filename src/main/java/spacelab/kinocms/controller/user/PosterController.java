package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.service.BannerBackgroundService;
import spacelab.kinocms.service.BannerBlockService;
import spacelab.kinocms.service.BannerService;
import spacelab.kinocms.service.FilmService;

@Controller
@RequestMapping("/posters")
@AllArgsConstructor
public class PosterController {

    private final BannerService bannerService;
    private final BannerBlockService bannerBlockService;
    private final FilmService filmService;
    private final BannerBackgroundService bannerBackgroundService;

    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("bannerBackground", bannerBackgroundService.getBannerBackground(1L));
        model.addAttribute("title", "Главная");

        model.addAttribute("banners", bannerService.getAllBanners());
        model.addAttribute("bannerBlocks", bannerBlockService.getBannerBlock(1L));
        model.addAttribute("bannerBackground", bannerBackgroundService.getBannerBackground(1L));
        model.addAttribute("currentFilm", filmService.getAllCurrentFilm());
        model.addAttribute("futureFilm", filmService.getAllFutureFilm());

        return new ModelAndView("user/posters");
    }
}
