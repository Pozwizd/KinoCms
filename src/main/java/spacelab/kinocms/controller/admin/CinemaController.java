package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.CinemaDto;
import spacelab.kinocms.Mapper.CinemaMapper;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.service.CinemaService;
import spacelab.kinocms.service.HallService;
import spacelab.kinocms.service.ImageCinemaService;

@Controller
@AllArgsConstructor
@RequestMapping("admin/cinema")
public class CinemaController {

    private final CinemaService cinemaService;
    private final HallService hallService;
    private final ImageCinemaService imageCinemaService;
    private final UploadFile uploadFile;
    private final CinemaMapper cinemaMapper;

    @GetMapping({"/", ""})
    public ModelAndView index(Model model){
        model.addAttribute("title", "Кинотеатр");
        model.addAttribute("pageActive", "cinema");
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        return new ModelAndView("admin/cinemas/cinemas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCinema(Model model, @PathVariable String id){
        model.addAttribute("title", "Кинотеатр");
        model.addAttribute("pageActive", "cinema");
        Cinema cinema =  cinemaService.getCinema(Long.parseLong(id));
        model.addAttribute("cinema", cinema );
        return new ModelAndView("admin/cinemas/cinemaEdit");
    }



    @PostMapping({"/edit/{id}",})
    public ModelAndView editBasicPage(@Valid @ModelAttribute("cinema") CinemaDto cinemaDto,
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
        cinemaService.updateCinema(cinemaMapper.toEntity(cinemaDto));
        return new ModelAndView("redirect:/admin/cinema");
    }

    @GetMapping({"/createCinema"})
    public ModelAndView createNewCinema(Model model) {

        Cinema cinema = new Cinema();
        cinema.setId(cinemaService.idLastCinema() + 1);
        cinema.setName("Новый кинотеатр");
        model.addAttribute("title", "Новый кинотеатр");
        model.addAttribute("pageActive", "cinema");
        model.addAttribute("cinema", cinema);
        return new ModelAndView("admin/cinemas/cinemaEdit");
    }

    @GetMapping("/removeCinema/{id}")
    public ModelAndView removeCinema(@PathVariable String id, HttpServletRequest request) {
        cinemaService.deleteCinema(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }
}
