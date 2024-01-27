package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;
import spacelab.kinocms.service.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("admin/cinema")
public class CinemaController {

    private final CinemaService cinemaService;
    private final HallService hallService;
    private final ImageCinemaService imageCinemaService;
    private final UploadFile uploadFile;

    @GetMapping({"/", ""})
    public ModelAndView index(Model model){
        model.addAttribute("title", "Кинотеатр");
        model.addAttribute("pageActive", "cinema");
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        return new ModelAndView("admin/cinemas/cinemas");
    }

    @GetMapping("/editCinema/{id}")
    public ModelAndView editCinema(Model model, @PathVariable String id){
        model.addAttribute("title", "Кинотеатр");
        model.addAttribute("pageActive", "cinema");
        model.addAttribute("cinema", cinemaService.getCinema(Long.parseLong(id)));
        return new ModelAndView("admin/cinemas/cinemaEdit");
    }



    @PostMapping("/editCinema/{id}")
    public ModelAndView editBasicPage(@ModelAttribute Cinema cinema,
                                      @PathVariable String id) {

        cinemaService.saveCinemaDto(cinema);
        return new ModelAndView("redirect:/admin/cinema");
    }

    @GetMapping({"/createCinema",})
    public ModelAndView createNewCinema(Model model, HttpServletRequest  request) {

        Cinema cinema = new Cinema();
        cinema.setName("Новый кинотеатр");
        cinemaService.saveCinema(cinema);
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

    @PostMapping("/removeCinema/{id}")
    public ModelAndView removeCinema(Model model, @PathVariable String id, HttpServletRequest request) {
        cinemaService.deleteCinema(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

// Ajax ====================================================================

    @GetMapping("/editCinema/showLogoPath/{id}")
    @ResponseBody
    public String showMainImageNews(Model model, @PathVariable long id) {
        Cinema cinema = cinemaService.getCinema(id);
        System.out.println(cinema.getLogoPath());
        return cinema.getLogoPath();
    }

    @PostMapping("/editCinema/editLogoPath/{id}")
    @ResponseBody
    public ResponseEntity<String> editLogoPath(@RequestPart("file") MultipartFile file,
                                                    @PathVariable Long id) {

        Cinema cinema = cinemaService.getCinema(id);
        cinema.setLogoPath(uploadFile.uploadFile(file,cinema.getLogoPath()));
        cinemaService.saveCinema(cinema);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editCinema/deleteLogoPath/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCinema(Model model, @PathVariable long id) {
        Cinema cinema = cinemaService.getCinema(id);
        uploadFile.deleteFile(cinema.getLogoPath());
        cinema.setLogoPath(null);
        cinemaService.saveCinema(cinema);
        return ResponseEntity.ok("Файл успешно удален");
    }

//    TopBanner

    @GetMapping("/editCinema/showTopBanner/{id}")
    @ResponseBody
    public String showTopBannerImage(Model model, @PathVariable long id) {
        Cinema cinema = cinemaService.getCinema(id);
        return cinema.getTopBanner();
    }

    @PostMapping("/editCinema/editTopBanner/{id}")
    @ResponseBody
    public ResponseEntity<String> editTopBanner(@RequestPart("file") MultipartFile file,
                                               @PathVariable Long id) {

        Cinema cinema = cinemaService.getCinema(id);
        cinema.setTopBanner(uploadFile.uploadFile(file,cinema.getLogoPath()));
        cinemaService.saveCinema(cinema);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editCinema/deleteTopBanner/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTopBanner(Model model, @PathVariable long id) {
        Cinema cinema = cinemaService.getCinema(id);
        uploadFile.deleteFile(cinema.getTopBanner());
        cinema.setTopBanner(null);
        cinemaService.saveCinema(cinema);
        return ResponseEntity.ok("Файл успешно удален");
    }


    @GetMapping("/editCinema/showAllImages/{id}")
    @ResponseBody
    public List<ImageCinema> showAllImages(@PathVariable String id) {
        Cinema cinema = cinemaService.getCinema(Long.parseLong(id));
        List<ImageCinema> imageCinemaList = imageCinemaService.getAllImageCinemaByCinema(cinema);
        System.out.println(imageCinemaList);
        return imageCinemaList;
    }

    @GetMapping("/editCinema/getImage/{id}")
    @ResponseBody
    public ImageCinema getImage(@PathVariable String id) {
        return imageCinemaService.getImageCinema(Long.parseLong(id));
    }

    @GetMapping("/editCinema/deleteImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        imageCinemaService.deleteImageCinema(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/editCinema/createNewImage/{id}")
    @ResponseBody
    public ImageCinema createImageNews(@PathVariable String id) {
        ImageCinema imageCinema = new ImageCinema();
        Cinema cinema = cinemaService.getCinema(Long.parseLong(id));
        imageCinema.setCinema(cinema);
        imageCinemaService.saveImageCinema(imageCinema);
        return imageCinemaService.getLastImageCinemaByCinema(cinema);
    }

    @PostMapping("/editCinema/editImageCinema/{id}")
    @ResponseBody
    public ResponseEntity<String> editImageNews(@RequestPart("file") MultipartFile file,
                                                @PathVariable Long id) {
        ImageCinema imageCinema = imageCinemaService.getImageCinema(id);
        imageCinema.setUrl(uploadFile.uploadFile(file,imageCinema.getUrl()));
        imageCinemaService.saveImageCinema(imageCinema);

        return ResponseEntity.ok("Файл успешно загружен");
    }

}
