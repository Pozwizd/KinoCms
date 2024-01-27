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
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;
import spacelab.kinocms.model.ImagesEntity.ImageHall;
import spacelab.kinocms.service.CinemaService;
import spacelab.kinocms.service.HallService;
import spacelab.kinocms.service.ImageCinemaService;
import spacelab.kinocms.service.ImageHallService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/cinema")
public class HallController {

    private final HallService hallService;
    private final CinemaService cinemaService;
    private final ImageCinemaService imageCinemaService;
    private final ImageHallService imageHallService;
    private final UploadFile uploadFile;



    @GetMapping("/editHall/{id}")
    public ModelAndView editHall(Model model, @PathVariable String id){
        model.addAttribute("hall", hallService.getHall(Long.parseLong(id)));
        return new ModelAndView("admin/cinemas/hallEdit");
    }



    @PostMapping("/editHall/{id}")
    public ModelAndView editHall(@ModelAttribute Hall hall,
                                      @PathVariable String id) {

        hallService.saveHallPage(hall);
        return new ModelAndView("redirect:/admin/cinema/editCinema/"+ hall.getCinema().getId());
    }

    @GetMapping("/createHall/{id}")
    public ModelAndView createHall(Model model, HttpServletRequest  request, @PathVariable String id) {
        Hall hall = new Hall();
        hall.setDateCreated(Date.valueOf(LocalDate.now()));
        hall.setCinema(cinemaService.getCinema(Long.parseLong(id)));
        hallService.saveHall(hall);
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

    @GetMapping("/removeHall/{id}")
    public ModelAndView removeHall(Model model, @PathVariable String id, HttpServletRequest request) {
        hallService.deleteHall(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

// Ajax ====================================================================

    @GetMapping("/editHall/showSchemeHall/{id}")
    @ResponseBody
    public String showSchemeHall(Model model, @PathVariable long id) {
        Hall hall = hallService.getHall(id);
        return hall.getUrlSchemeImageHall();
    }

    @PostMapping("/editHall/editSchemeHall/{id}")
    @ResponseBody
    public ResponseEntity<String> editSchemeHall(@RequestPart("file") MultipartFile file,
                                               @PathVariable Long id) {


        Hall hall = hallService.getHall(id);
        hall.setUrlSchemeImageHall(uploadFile.uploadFile(file,hall.getUrlSchemeImageHall()));
        hallService.saveHall(hall);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editHall/deleteSchemeHall/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteSchemeHall(Model model, @PathVariable long id) {
        Hall hall = hallService.getHall(id);
        uploadFile.deleteFile(hall.getUrlSchemeImageHall());
        hall.setUrlSchemeImageHall(null);
        hallService.saveHall(hall);
        return ResponseEntity.ok("Файл успешно удален");
    }

//    TopBanner

    @GetMapping("/editHall/showTopBanner/{id}")
    @ResponseBody
    public String showTopBannerImage(Model model, @PathVariable long id) {
        Hall hall = hallService.getHall(id);
        return hall.getTopBanner();
    }

    @PostMapping("/editHall/editTopBanner/{id}")
    @ResponseBody
    public ResponseEntity<String> editTopBanner(@RequestPart("file") MultipartFile file,
                                                @PathVariable Long id) {

        Hall hall = hallService.getHall(id);
        hall.setTopBanner(uploadFile.uploadFile(file,hall.getTopBanner()));
        hallService.saveHall(hall);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editHall/deleteTopBanner/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTopBanner(Model model, @PathVariable long id) {
        Hall hall = hallService.getHall(id);
        uploadFile.deleteFile(hall.getTopBanner());
        hall.setTopBanner(null);
        hallService.saveHall(hall);
        return ResponseEntity.ok("Файл успешно удален");
    }

    @GetMapping("/editHall/showAllImages/{id}")
    @ResponseBody
    public List<ImageHall> showAllImages(@PathVariable String id) {
        Hall  hall = hallService.getHall(Long.parseLong(id));
        return imageHallService.getAllImageHallByHall(hall);
    }

    @GetMapping("/editHall/getImage/{id}")
    @ResponseBody
    public ImageHall getImage(@PathVariable String id) {
        return imageHallService.getImageHall(Long.parseLong(id));
    }

    @GetMapping("/editHall/deleteImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        imageHallService.deleteImageHall(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/editHall/createNewImage/{id}")
    @ResponseBody
    public ImageHall createImageHall(@PathVariable String id) {
        ImageHall imageHall = new ImageHall();
        Hall hall = hallService.getHall(Long.parseLong(id));
        imageHall.setHall(hall);
        imageHallService.saveImageHall(imageHall);
        return imageHallService.getLastImageHallByHall(hall);

    }

    @PostMapping("/editHall/editImageHall/{id}")
    @ResponseBody
    public ResponseEntity<String> editImageHall(@RequestPart("file") MultipartFile file,
                                                @PathVariable Long id) {
        ImageHall imageHall = imageHallService.getImageHall(id);
        imageHall.setUrl(uploadFile.uploadFile(file,imageHall.getUrl()));
        imageHallService.saveImageHall(imageHall);
        return ResponseEntity.ok("Файл успешно загружен");
    }


}
