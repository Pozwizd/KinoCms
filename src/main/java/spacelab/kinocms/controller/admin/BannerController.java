package spacelab.kinocms.controller.admin;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.*;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.banners.*;
import spacelab.kinocms.service.*;

import java.util.List;
import java.util.stream.Collectors;


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
    public ModelAndView index(Model model, HttpServletRequest request, ServletContext servletContext) {
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

    @PostMapping("/editBlockMainBanners/")
    @ResponseBody
    public ResponseEntity<?> editMainBannerBlock(@ModelAttribute("bannerBlock")
                                                 @Valid BannerBlockUpdateDTO bannerBlockUpdateDTO,
                                                 BindingResult bindingResult) {
        List<Banner> banners = bannerService.getAllBanners();
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        BannerBlock bannerBlock = bannerBlockService.getBannerBlock(1L);
        bannerBlock.setStatus(bannerBlockUpdateDTO.getStatus());
        bannerBlock.setTimeChange(String.valueOf(bannerBlockUpdateDTO.getTimeChange()));

        if (bannerBlockUpdateDTO.getBanners() != null) {
            bannerBlockUpdateDTO.getBanners().removeIf(bannerUpdateDTO -> bannerUpdateDTO.getId() == null);
            for (BannerUpdateDTO bannerUpdateDTO : bannerBlockUpdateDTO.getBanners()) {
                if (!uploadFile.isAllowedImageTypeAndSize(bannerUpdateDTO.getPathImage())
                        && !banners.stream().map(Banner::getId)
                        .toList().contains(Long.parseLong(bannerUpdateDTO.getId()))) {
                    return ResponseEntity.badRequest().body("Недопустимый тип файла");
                }
            }

            // Удалить баннера айди которых нет в списке
            List<Long> ids = bannerBlockUpdateDTO
                    .getBanners()
                    .stream()
                    .map(BannerUpdateDTO::getId)
                    .map(Long::parseLong).collect(Collectors.toList());



            banners.stream().filter(banner -> !ids.contains(banner.getId()))
                    .forEach(bannerService::deleteBanner);

            for (BannerUpdateDTO bannerUpdateDTO : bannerBlockUpdateDTO.getBanners()) {
                if (bannerUpdateDTO.getId() == null) {
                    continue;
                }
                Banner banner = bannerService.getBanner(Long.parseLong(bannerUpdateDTO.getId()));
                banner.setBannerBlock(bannerBlock);
                banner.setId(Long.parseLong(bannerUpdateDTO.getId()));
                banner.setUrl(bannerUpdateDTO.getUrl());
                banner.setTitle(bannerUpdateDTO.getTitle());
                if (!bannerUpdateDTO.getPathImage().isEmpty()) {
                    banner.setPathImage(uploadFile.uploadFile(bannerUpdateDTO.getPathImage(), banner.getPathImage()));
                }
                bannerService.saveBanner(banner);
            }

            return ResponseEntity.ok("Файл успешно загружен");
        }
        for (Banner banner : banners) {
            bannerService.deleteBanner(banner.getId());
        }

        return ResponseEntity.ok("Файл успешно загружен");

    }


    @PostMapping("/editBackgroundBanner/")
    @ResponseBody
    public ResponseEntity<String> editBackgroundBanner(@RequestParam("backgroundImageDefault") Boolean isDefault,
                                                       @RequestParam("backgroundImage") MultipartFile file) {

        if (isDefault == null) {
            return ResponseEntity.badRequest().body("Ошибка");
        }

        BannerBackground bannerBackground = bannerBackgroundService.getBannerBackground(1L);
        bannerBackground.setIsDefault(isDefault);
        bannerBackground.setUrl(uploadFile.uploadFile(file, bannerBackground.getUrl()));
        bannerBackgroundService.saveBannerBackground(bannerBackground);
        return ResponseEntity.ok("Файл успешно загружен");

    }


    @PostMapping("/editBannerBlockForNewsAndStocks/")
    @ResponseBody
    public ResponseEntity<?> editBannerBlockForNewsAndStocks(@ModelAttribute("bannerBlockForNewsAndStocks")
                                                 @Valid BannerForNewsAndStockBlockDto bannerForNewsAndStockBlockDto,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks = bannerBlockForNewsAndStocksService.getBannerBlockForNewsAndStocks(1L);
        bannerBlockForNewsAndStocks
                .setStatusBlockBannerForNewsAndStocks(
                        bannerForNewsAndStockBlockDto.getStatusBlockBannerForNewsAndStocks());
        bannerBlockForNewsAndStocks.
                setTimeChangeBlockBannerForNewsAndStocks(
                        bannerForNewsAndStockBlockDto.getTimeChangeBlockBannerForNewsAndStocks());

        List<BannerForNewsAndStocks> banners = bannerForNewsAndStocksService.getAllBannerForNewsAndStocks();

        if (bannerForNewsAndStockBlockDto.getBanners() != null) {

            bannerForNewsAndStockBlockDto.getBanners().removeIf(bannerUpdateDTO -> bannerUpdateDTO.getId() == null);
            for (BannerForNewsAndStocksItemDto bannerUpdateDTO : bannerForNewsAndStockBlockDto.getBanners()) {
                if (!uploadFile.isAllowedImageTypeAndSize(bannerUpdateDTO.getPathImage())
                        && !banners.stream().map(BannerForNewsAndStocks::getId).toList().contains(bannerUpdateDTO.getId())) {
                    return ResponseEntity.badRequest().body("Недопустимый тип файла");
                }
            }



            // Удалить баннера айди которых нет в списке
            List<Long> ids = bannerForNewsAndStockBlockDto
                    .getBanners()
                    .stream()
                    .map(BannerForNewsAndStocksItemDto::getId).toList();


            banners.stream().filter(banner -> !ids.contains(banner.getId()))
                    .forEach(bannerForNewsAndStocksService::deleteBannerForNewsAndStocks);

            for (BannerForNewsAndStocksItemDto bannerUpdateDTO : bannerForNewsAndStockBlockDto.getBanners()) {
                if (bannerUpdateDTO.getId() == null) {
                    continue;
                }
                BannerForNewsAndStocks bannerForNewsAndStocks = bannerForNewsAndStocksService.getBannerForNewsAndStocksById(bannerUpdateDTO.getId());
                bannerForNewsAndStocks.setBannerBlockForNewsAndStocks(bannerBlockForNewsAndStocks);
                bannerForNewsAndStocks.setId(bannerUpdateDTO.getId());
                bannerForNewsAndStocks.setUrl(bannerUpdateDTO.getUrl());
                bannerForNewsAndStocks.setTitle(bannerUpdateDTO.getTitle());
                if (!bannerUpdateDTO.getPathImage().isEmpty()) {
                    bannerForNewsAndStocks.setPathImage(
                            uploadFile
                                    .uploadFile(
                                            bannerUpdateDTO.getPathImage(),
                                            bannerForNewsAndStocks.getPathImage()));
                }
                bannerForNewsAndStocksService.saveBannerForNewsAndStocks(bannerForNewsAndStocks);
            }

            for (BannerForNewsAndStocks banner : banners) {
                bannerForNewsAndStocksService.deleteBannerForNewsAndStocks(banner);
            }

            return ResponseEntity.ok("Файл успешно загружен");

        }
        return ResponseEntity.ok("Файл успешно загружен");
    }



}
