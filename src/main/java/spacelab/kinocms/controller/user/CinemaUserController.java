package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.service.*;

@Controller
@AllArgsConstructor
@RequestMapping( "/cinema")
public class CinemaUserController {
    private final BannerService bannerService;
    private final BannerBlockService bannerBlockService;
    private final FilmService filmService;
    private final BannerBackgroundService bannerBackgroundService;
    private final MainPageService mainPageService;
    private final CinemaService cinemaService;
    private final HallService hallService;


    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {

        model.addAttribute("title", "Кинотеатры");
        model.addAttribute("mainPage", mainPageService.getMainPage());

        model.addAttribute("banners", bannerService.getAllBanners());
        model.addAttribute("bannerBlocks", bannerBlockService.getBannerBlock(1L));
        model.addAttribute("bannerBackground", bannerBackgroundService.getBannerBackground(1L));
        model.addAttribute("currentFilm", filmService.getAllCurrentFilm());
        model.addAttribute("futureFilm", filmService.getAllFutureFilm());

        model.addAttribute("cinemas", cinemaService.getAllCinemas());

        return new ModelAndView("user/cinema/cinemas");
    }

    @GetMapping("/{id}")
    public ModelAndView getCinema(Model model, @PathVariable String id) {
        model.addAttribute("title", cinemaService.getCinema(Long.parseLong(id)).getName());
        model.addAttribute("cinema", cinemaService.getCinema(Long.parseLong(id)));

        return new ModelAndView("user/cinema/cinemaCard");
    }

    @GetMapping({"/hall/{id}"})
    public ModelAndView hall(@PathVariable String id, Model model) {

        model.addAttribute("title", hallService.getHall(Long.parseLong(id)).getHallNumber());
        model.addAttribute("hall", hallService.getHall(Long.parseLong(id)));
        return new ModelAndView("user/cinema/hallCard");
    }
}
