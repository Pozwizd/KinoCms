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
import spacelab.kinocms.Dto.FilmDto;
import spacelab.kinocms.Mapper.FilmMapper;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;
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
        model.addAttribute("film", filmMapper.toDto(film));
        model.addAttribute("filmTypeFilm", film.getTypeFilm());

        List<TypeFilm> filmType = List.of(TypeFilm.Twodimensional, TypeFilm.Threedimensional, TypeFilm.IMAX);
        model.addAttribute("typeFilm", filmType);

        return new ModelAndView("admin/film/editFilm");

    }

    @PostMapping("/editFilm/{id}")
    public ModelAndView editFilm(@Valid @ModelAttribute("film") FilmDto film,
                                 BindingResult bindingResult,
                                 @RequestParam("filmTypes") List<TypeFilm> filmTypes, Model model) {
        if (bindingResult.hasErrors()||filmTypes.isEmpty()) {
            model.addAttribute("title", "Редактирование фильма " + filmService.getFilm(film.getId()).getName());
            model.addAttribute("pageActive", "film");
            model.addAttribute("filmTypeFilm", filmService.getFilm(film.getId()).getTypeFilm());
            List<TypeFilm> filmType = List.of(TypeFilm.Twodimensional, TypeFilm.Threedimensional, TypeFilm.IMAX);
            model.addAttribute("typeFilm", filmType);
            model.addAttribute("typeFilmError",
                    true);
            return new ModelAndView("admin/film/editFilm");
        }

        film.setTypeFilm(filmTypes);


        filmService.updateFilm(filmMapper.toEntity(film));
        return new ModelAndView("redirect:/admin/films");
    }

    @GetMapping("/createFilm")
    public ModelAndView createFilm(Model model, HttpServletRequest request) {
        Film film = new Film();
        film.setName("Новый фильм");
        filmService.saveFilm(film);
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

    @PostMapping("/removeFilm/{id}")
    public ModelAndView removeFilm(Model model, @PathVariable String id, HttpServletRequest request) {
        filmService.deleteFilm(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

// Ajax ====================================================================

    @GetMapping("/editFilm/showMainPage/{id}")
    @ResponseBody
    public String showMainImageFilm(Model model, @PathVariable long id) {
        Film film = filmService.getFilm(id);
        return film.getMainImage();
    }

    @PostMapping("/editFilm/editMainImage/{id}")
    @ResponseBody
    public ResponseEntity<String> editMainImageFilm(@RequestPart("file") MultipartFile file,
                                                    @PathVariable Long id) {

        Film film = filmService.getFilm(id);
        film.setMainImage(uploadFile.uploadFile(file, film.getMainImage()));
        filmService.saveFilm(film);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editFilm/deleteMainImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImageFilm(Model model, @PathVariable long id) {
        Film film = filmService.getFilm(id);
        uploadFile.deleteFile(film.getMainImage());
        film.setMainImage(null);
        filmService.saveFilm(film);
        return ResponseEntity.ok("Файл успешно удален");
    }

    @GetMapping("/editFilm/showAllImageFilm/{id}")
    @ResponseBody
    public List<ImageFilm> showAllImages(@PathVariable String id) {
        return imageFilmService.getAllImageFilmByFilm(filmService.getFilm(Long.parseLong(id)));
    }

    @GetMapping("/editFilm/getImage/{id}")
    @ResponseBody
    public ImageFilm getImage(@PathVariable String id) {
        return imageFilmService.getImageFilmById(Long.parseLong(id));
    }

    @GetMapping("/editFilm/deleteImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        imageFilmService.deleteImageFilm(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/editFilm/createNewImage/{id}")
    @ResponseBody
    public ImageFilm createImageFilm(@PathVariable String id) {
        ImageFilm imageFilm = new ImageFilm();
        imageFilm.setFilm(filmService.getFilm(Long.parseLong(id)));
        imageFilmService.saveImageFilm(imageFilm);
        return imageFilmService.getLastImageFilmByFilm(filmService.getFilm(Long.parseLong(id)));
    }

    @PostMapping("/editFilm/editImageFilm/{id}")
    @ResponseBody
    public ResponseEntity<String> editImageFilm(@RequestPart("file") MultipartFile file,
                                                @PathVariable Long id) {

        ImageFilm  imageFilm = imageFilmService.getImageFilmById(id);
        imageFilm.setUrl(uploadFile.uploadFile(file,imageFilm.getUrl()));
        imageFilmService.saveImageFilm(imageFilm);
        return ResponseEntity.ok("Файл успешно загружен");
    }
}