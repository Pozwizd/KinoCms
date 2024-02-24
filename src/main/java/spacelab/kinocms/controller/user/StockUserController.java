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
@RequestMapping( "/stock")
public class StockUserController {

    private final BannerService bannerService;
    private final BannerBlockService bannerBlockService;
    private final FilmService filmService;
    private final BannerBackgroundService bannerBackgroundService;
    private final MainPageService mainPageService;
    private final CinemaService cinemaService;
    private final StockService stockService;


    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Главная");
        model.addAttribute("mainPage", mainPageService.getMainPage());

        model.addAttribute("stocks", stockService.listAllStocks());

        return new ModelAndView("user/stock/stocks");
    }

    @GetMapping("/{id}")
    public ModelAndView getCinema(Model model, @PathVariable String id) {
        model.addAttribute("title", "Кинотеатр");
        model.addAttribute("cinema", cinemaService.getCinema(Long.parseLong(id)));

        return new ModelAndView("user/stock/stockCard");
    }
}
