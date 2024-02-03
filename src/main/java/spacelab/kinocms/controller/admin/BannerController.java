package spacelab.kinocms.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.Banner;
import spacelab.kinocms.model.BannerBlockForNewsAndStocks;
import spacelab.kinocms.model.BannerForNewsAndStocks;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;
import spacelab.kinocms.repository.BannerBlockForNewsAndStocksRepository;
import spacelab.kinocms.service.*;

import java.util.List;


@Controller
@RequestMapping(value = "/admin/banners")
@AllArgsConstructor
public class BannerController {

    private final BannerService bannerService;
    private final BannerBlockService bannerBlockService;
    private final BannerBackgroundService bannerBackgroundService;
    private final BannerForNewsAndStocksService bannerForNewsAndStocksService;
    private final UploadFile uploadFile;
    private final BannerBlockForNewsAndStocksService bannerBlockForNewsAndStocksService;

    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Banners");
        model.addAttribute("pageActive", "banners");

        model.addAttribute("bannerBlock", bannerBlockService.getBannerBlock(1L));


        return new ModelAndView("admin/banners");
    }


    //  AJAX  ==========================================
//  Main banner
    @GetMapping("/showAllMainBanner/")
    @ResponseBody
    public List<Banner> showAllMailBanner() {
        return bannerService.getAllBanners();
    }

    @GetMapping("/getMainBanner/{id}")
    @ResponseBody
    public Banner getMainBanner(@PathVariable String id) {
        return bannerService.getBanner(Long.parseLong(id));
    }

    @GetMapping("/deleteMainBanner/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteMainBanner(@PathVariable String id) {
        bannerService.deleteBanner(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/createMainBanner/{id}")
    @ResponseBody
    public Banner createMainBanner(@PathVariable Long id) {
        Banner banner = new Banner();
        banner.setBannerBlock(bannerBlockService.getBannerBlock(id));
        bannerService.saveBanner(banner);

        return bannerService.getLastBannerByBannerBlock(bannerBlockService.getBannerBlock(id));
    }

    @PostMapping("/editMainBanner/{id}")
    @ResponseBody
    public ResponseEntity<String> editMainBanner(@RequestPart("file") MultipartFile file,
                                                 @PathVariable Long id) {

        Banner banner = bannerService.getBanner(id);
        banner.setUrl(uploadFile.uploadFile(file, banner.getUrl()));
        bannerService.saveBanner(banner);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    //  Stock and News banner

    @GetMapping("/showAllBannerForNewsAndStocks")
    @ResponseBody
    public List<BannerForNewsAndStocks> showAllBannerForNewsAndStocks() {
        return bannerForNewsAndStocksService.getAllBannerForNewsAndStocks();
    }

    @GetMapping("/getBannerForNewsAndStocks/{id}")
    @ResponseBody
    public BannerForNewsAndStocks getBannerForNewsAndStocks(@PathVariable Long id) {
        return bannerForNewsAndStocksService.getBannerForNewsAndStocksById(id);
    }

    @GetMapping("/deleteBannerForNewsAndStocks/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteBannerForNewsAndStocks(@PathVariable String id) {
        bannerForNewsAndStocksService.deleteBannerForNewsAndStocks(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/createBannerForNewsAndStocks/{id}")
    @ResponseBody
    public BannerForNewsAndStocks createBannerForNewsAndStocks(@PathVariable Long id) {
        BannerForNewsAndStocks bannerForNewsAndStocks = new BannerForNewsAndStocks();
        bannerForNewsAndStocks.setBannerBlockForNewsAndStocks(bannerBlockForNewsAndStocksService.getBannerBlockForNewsAndStocks(id));
        bannerForNewsAndStocksService.saveBannerForNewsAndStocks(bannerForNewsAndStocks);
        return null;
    }

    @PostMapping("/editBannerForNewsAndStocks/{id}")
    @ResponseBody
    public ResponseEntity<String> editBannerForNewsAndStocks(@RequestPart("file") MultipartFile file,
                                                             @PathVariable Long id) {

        BannerForNewsAndStocks bannerForNewsAndStocks = bannerForNewsAndStocksService.getBannerForNewsAndStocksById(id);
        bannerForNewsAndStocks.setUrl(uploadFile.uploadFile(file, bannerForNewsAndStocks.getUrl()));
        bannerForNewsAndStocksService.saveBannerForNewsAndStocks(bannerForNewsAndStocks);
        return ResponseEntity.ok("Файл успешно загружен");
    }

}
