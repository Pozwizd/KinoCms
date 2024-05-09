package spacelab.kinocms.controller.admin;

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
import spacelab.kinocms.Dto.CinemaDto;
import spacelab.kinocms.Dto.HallDto;
import spacelab.kinocms.Mapper.CinemaMapper;
import spacelab.kinocms.Mapper.HallMapper;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;
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
    private final HallMapper hallMapper;
    private final CinemaMapper cinemaMapper;


    @GetMapping("/editHall/{id}")
    public ModelAndView editHall(Model model, @PathVariable String id) {
        model.addAttribute("hall", hallService.getHall(Long.parseLong(id)));
        return new ModelAndView("admin/cinemas/hallEdit");
    }


    @PostMapping("/editHall/{id}")
    public ModelAndView editHall(@Valid @ModelAttribute("hall") HallDto hallDto,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Редактирование холла " + hallDto.getHallNumber());
            model.addAttribute("pageActive", "cinema");
            model.addAttribute("hall", hallService.getHall(hallDto.getId()));
            return new ModelAndView("admin/cinemas/hallEdit");
        }
        if(hallDto.getImagesHall() != null) {
            hallDto.getImagesHall().removeIf(imageCinemaDto -> imageCinemaDto.getId() == null
                    && imageCinemaDto.getUrl().getSize() == 0);
            if(hallDto
                    .getImagesHall()
                    .stream()
                    .anyMatch(image -> !uploadFile
                            .isAllowedImageTypeAndSize(image.getUrl()))){
                model.addAttribute("title", "Редактирование кинотеатра " + hallDto.getCinema().getName());
                model.addAttribute("pageActive", "cinema");
                model.addAttribute("hall", hallService.getHall(hallDto.getId()));
                return new ModelAndView("admin/cinemas/hallEdit");
            }

        }
        Hall hall = hallMapper.toEntity(hallDto);
        hallService.updateHall(hall);
        return new ModelAndView("redirect:/admin/cinema/editCinema/" + hall.getCinema().getId());
    }

    @PostMapping("/createHall/")
    public ModelAndView createHall(@Valid @ModelAttribute("cinema") CinemaDto cinemaDto,
                                   BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("title", "Редактирование кинотеатра " + cinemaDto.getName());
            model.addAttribute("pageActive", "cinema");
            Cinema cinema =  cinemaService.getCinema(cinemaDto.getId());
            cinema.setId(cinemaDto.getId());
            model.addAttribute("cinema", cinema);
            return new ModelAndView("admin/cinemas/cinemaEdit");
        }
        if(cinemaDto.getImagesCinema() != null) {
            cinemaDto.getImagesCinema().removeIf(imageCinemaDto -> imageCinemaDto.getId() == null
                    && imageCinemaDto.getUrl().getSize() == 0);
            if(cinemaDto
                    .getImagesCinema()
                    .stream()
                    .anyMatch(image -> !uploadFile
                            .isAllowedImageTypeAndSize(image.getUrl()))){
                model.addAttribute("title", "Редактирование кинотеатра " + cinemaDto.getName());
                model.addAttribute("pageActive", "cinema");
                model.addAttribute("cinema", cinemaService.getCinema(cinemaDto.getId()));
                return new ModelAndView("admin/cinemas/cinemaEdit");
            }

        }
        Cinema cinema = cinemaMapper.toEntity(cinemaDto);
        cinemaService.updateCinema(cinema);

        Hall hall = new Hall();
        hall.setId(hallService.idLastHall() + 1);
        hall.setDateCreated(Date.valueOf(LocalDate.now()));
        hall.setCinema(cinema);
        model.addAttribute("hall", hall);
        return new ModelAndView("admin/cinemas/hallEdit");
    }

    @GetMapping("/removeHall/{id}")
    public ModelAndView removeHall(Model model, @PathVariable String id, HttpServletRequest request) {
        hallService.deleteHall(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

}
