package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.service.*;

@Controller
@AllArgsConstructor
@RequestMapping( "/")
public class MainPageController {

    private final BannerService bannerService;
    private final BannerBlockService bannerBlockService;
    private final BannerForNewsAndStocksService bannerForNewsAndStocksService;
    private final BannerBlockForNewsAndStocksService bannerBlockForNewsAndStocksService;
    private final FilmService filmService;
    private final BannerBackgroundService bannerBackgroundService;
    private final MainPageService mainPageService;

    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Главная");
        model.addAttribute("mainPage", mainPageService.getMainPage());
        model.addAttribute("banners", bannerService.getAllBanners());
        model.addAttribute("bannerBlocks", bannerBlockService.getBannerBlock(1L));
        model.addAttribute("bannerForNewsAndStocks", bannerForNewsAndStocksService.getAllBannerForNewsAndStocks());
        model.addAttribute("bannerBlockForNewsAndStocks", bannerBlockForNewsAndStocksService.getBannerBlockForNewsAndStocks(1L));
        model.addAttribute("bannerBackground", bannerBackgroundService.getBannerBackground(1L));
        model.addAttribute("currentFilm", filmService.getAllCurrentFilm());
        model.addAttribute("futureFilm", filmService.getAllFutureFilm());

        return new ModelAndView("user/index");
    }

}
