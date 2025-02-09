package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.FilmDto;
import spacelab.kinocms.Mapper.FilmMapper;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.entity.Film;
import spacelab.kinocms.service.FilmService;
import spacelab.kinocms.service.ImageFilmService;

import java.util.List;

@Controller
@RequestMapping("admin/films")
@AllArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final ImageFilmService imageFilmService;
    private final FilmMapper filmMapper;
    private final UploadFile uploadFile;



    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Фильмы");
        model.addAttribute("pageActive", "film");

        model.addAttribute("filmsList", filmService.getAllCurrentFilm());
        model.addAttribute("filmsFutureList", filmService.getAllFutureFilm());

        return new ModelAndView("admin/film/films");
    }

    @GetMapping("/editFilm/{id}")
    public ModelAndView editFilm(Model model, @PathVariable String id) {

        Film film = filmService.getFilm(Long.parseLong(id));
        model.addAttribute("title", "Редактирование фильма " + film.getName());
        model.addAttribute("pageActive", "film");
        model.addAttribute("film", film);
        model.addAttribute("filmTypeFilm", film.getTypeFilm());

        List<TypeFilm> filmType = List.of(TypeFilm.Twodimensional, TypeFilm.Threedimensional, TypeFilm.IMAX);
        model.addAttribute("typeFilm", filmType);

        return new ModelAndView("admin/film/editFilm");

    }

    @PostMapping("/editFilm/{id}")
    public ModelAndView editFilm(@Valid @ModelAttribute("film") FilmDto filmDto,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Редактирование фильма " + filmDto.getName());
            model.addAttribute("pageActive", "film");
            model.addAttribute("film", filmService.getFilm(filmDto.getId()));
            return new ModelAndView("admin/film/editFilm");
        }
        if(filmDto.getImagesFilm() != null) {
            filmDto.getImagesFilm().removeIf(imageFilm -> imageFilm.getId() == null
                    && imageFilm.getUrl().getSize() == 0);
            if(filmDto
                    .getImagesFilm()
                    .stream()
                    .anyMatch(image -> !uploadFile
                            .isAllowedImageTypeAndSize(image.getUrl()))){
                model.addAttribute("title", "Редактирование фильма " + filmDto.getName());
                model.addAttribute("pageActive", "film");
                model.addAttribute("film", filmService.getFilm(filmDto.getId()));
                return new ModelAndView("admin/film/editFilm");
            }

        }
        filmService.updateFilm(filmMapper.toEntity(filmDto));
        return new ModelAndView("redirect:/admin/films");
    }

    @GetMapping("/createFilm")
    public ModelAndView createFilm(Model model, HttpServletRequest request) {
        Film film = new Film();
        film.setId(filmService.idLastFilm() + 1);
        film.setName("Новый фильм");
        film.setMainImage(null);

        return new ModelAndView("admin/film/editFilm", "film", film);
    }

    @GetMapping("/removeFilm/{id}")
    public ModelAndView removeFilm(Model model, @PathVariable String id, HttpServletRequest request) {
        filmService.deleteFilm(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }


}
