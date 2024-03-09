package spacelab.kinocms.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.Dto.BannerForNewsAndStockBlockDto;
import spacelab.kinocms.Dto.BannerForNewsAndStocksItemDto;
import spacelab.kinocms.Dto.MainBannersBlockDto;
import spacelab.kinocms.Dto.MainBannersItemDto;
import spacelab.kinocms.model.banners.*;
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

        model.addAttribute("bannerBackground",
                bannerBackgroundService.getBannerBackground(1L));

        model.addAttribute("bannerBlock", bannerBlockService.getBannerBlock(1L));
        model.addAttribute("bannerBlockForNewsAndStocks", bannerBlockForNewsAndStocksService
                .getBannerBlockForNewsAndStocks(1L));

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

    @GetMapping("/createMainBanner/")
    @ResponseBody
    public List<Banner> createMainBanner() {
        Banner banner = new Banner();
        banner.setBannerBlock(bannerBlockService.getBannerBlock(1L));
        bannerService.saveBanner(banner);
        return List.of(bannerService.getLastBannerByBannerBlock(bannerBlockService.getBannerBlock(1L)));
    }

    @PostMapping("/editMainBanner/{id}")
    @ResponseBody
    public ResponseEntity<String> editMainBanner(@RequestPart("file") MultipartFile file,
                                                 @PathVariable Long id) {

        Banner banner = bannerService.getBanner(id);
        banner.setPathImage(uploadFile.uploadFile(file, banner.getPathImage()));
        bannerService.saveBanner(banner);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editAllMainBanners/")
    @ResponseBody
    public ResponseEntity<String> editMainBannerBlock(@RequestBody MainBannersBlockDto mainBannersBlockDto) {
        BannerBlock bannerBlock = bannerBlockService.getBannerBlock(1L);
        bannerBlock.setTimeChange(mainBannersBlockDto.getTimeChange().toString());
        bannerBlock.setStatus(mainBannersBlockDto.getStatus());
        bannerBlockService.saveBannerBlock(bannerBlock);
        for(MainBannersItemDto mainBannersItemDto : mainBannersBlockDto.getMainBannersItemDto()) {
            Banner banner = bannerService.getBanner(mainBannersItemDto.getId());
            banner.setUrl(mainBannersItemDto.getUrl());
            banner.setTitle(mainBannersItemDto.getText());
            bannerService.saveBanner(banner);
        }

        return ResponseEntity.ok("Файл успешно загружен");
    }

    //  Background banner

    @GetMapping("/getBackgroundBanner/")
    @ResponseBody
    public BannerBackground getBackgroundBanner() {
        return bannerBackgroundService.getBannerBackground(1L);
    }


    @PostMapping("/editBackgroundBanner/")
    @ResponseBody
    public ResponseEntity<String> editBackgroundBanner(@RequestPart("file") MultipartFile file) {
        BannerBackground bannerBackground = bannerBackgroundService.getBannerBackground(1L);
        bannerBackground.setUrl(uploadFile.uploadFile(file, bannerBackground.getUrl()));
        bannerBackgroundService.saveBannerBackground(bannerBackground);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/deleteBackgroundBanner/")
    @ResponseBody
    public ResponseEntity<String> deleteBackgroundBanner() {
        BannerBackground bannerBackground = bannerBackgroundService.getBannerBackground(1L);
        bannerBackground.setUrl(null);
        bannerBackgroundService.saveBannerBackground(bannerBackground);
        return ResponseEntity.ok("Image deleted successfully");
    }

    @PostMapping("/changeBackgroundBannerBlock/")
    @ResponseBody
    public ResponseEntity<String> changeBackgroundBanner(@RequestParam("radioButton") Boolean radioButtonValue) {
        BannerBackground  bannerBackground = bannerBackgroundService.getBannerBackground(1L);
        bannerBackground.setIsDefault(radioButtonValue);
        bannerBackgroundService.saveBannerBackground(bannerBackground);
        return ResponseEntity.ok("Файл успешно загружен");
    }


    //  Stock and News banner

    @GetMapping("/showAllBannerForNewsAndStocks/")
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

    @GetMapping("/createBannerForNewsAndStocks/")
    @ResponseBody
    public List<BannerForNewsAndStocks> createBannerForNewsAndStocks() {
        BannerForNewsAndStocks bannerForNewsAndStocks = new BannerForNewsAndStocks();
        bannerForNewsAndStocks.setBannerBlockForNewsAndStocks(bannerBlockForNewsAndStocksService.getBannerBlockForNewsAndStocks(1L));
        bannerForNewsAndStocksService.saveBannerForNewsAndStocks(bannerForNewsAndStocks);
        BannerForNewsAndStocks bannerForNewsAndStocksDB = bannerForNewsAndStocksService.getLastBannerForNewsAndStocks();
        return List.of(bannerForNewsAndStocksDB);
    }

    @PostMapping("/editBannerForNewsAndStocks/{id}")
    @ResponseBody
    public ResponseEntity<String> editBannerForNewsAndStocks(@RequestPart("file") MultipartFile file,
                                                             @PathVariable Long id) {

        BannerForNewsAndStocks bannerForNewsAndStocks = bannerForNewsAndStocksService.getBannerForNewsAndStocksById(id);
        bannerForNewsAndStocks.setPathImage(uploadFile.uploadFile(file, bannerForNewsAndStocks.getPathImage()));
        bannerForNewsAndStocksService.saveBannerForNewsAndStocks(bannerForNewsAndStocks);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editAllBannerForNewsAndStocks/")
    @ResponseBody
    public ResponseEntity<String> editBannerForNewsAndStocksBlock(@RequestBody BannerForNewsAndStockBlockDto bannerForNewsAndStockBlockDto) {
        BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks
                = bannerBlockForNewsAndStocksService.getBannerBlockForNewsAndStocks(1L);
        bannerBlockForNewsAndStocks.setTimeChangeBlockBannerForNewsAndStocks(bannerForNewsAndStockBlockDto.getTimeChangeBlockBannerForNewsAndStocks());
        bannerBlockForNewsAndStocks.setStatusBlockBannerForNewsAndStocks(bannerForNewsAndStockBlockDto.getStatusBlockBannerForNewsAndStocks());
        bannerBlockForNewsAndStocksService.saveBannerBlockForNewsAndStocks(bannerBlockForNewsAndStocks);
        for(BannerForNewsAndStocksItemDto bannerForNewsAndStocksItemDto : bannerForNewsAndStockBlockDto.getMainBannersItemDto()) {
            BannerForNewsAndStocks bannerForNewsAndStocks = bannerForNewsAndStocksService.getBannerForNewsAndStocksById(bannerForNewsAndStocksItemDto.getId());
            bannerForNewsAndStocks.setUrl(bannerForNewsAndStocksItemDto.getUrl());
            bannerForNewsAndStocks.setTitle(bannerForNewsAndStocksItemDto.getText());
            bannerForNewsAndStocksService.saveBannerForNewsAndStocks(bannerForNewsAndStocks);
        }


        return ResponseEntity.ok("Файл успешно загружен");
    }

}
