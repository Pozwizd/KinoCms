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
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;
import spacelab.kinocms.service.FilmService;
import spacelab.kinocms.service.ImageFilmService;
import spacelab.kinocms.service.ImageNewsService;
import spacelab.kinocms.service.NewsService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin/films")
@AllArgsConstructor
public class FilmController {

    private final FilmService filmService;

    private final ImageFilmService imageFilmService;

    private final UploadFile uploadFile;



    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Фильмы");
        model.addAttribute("pageActive", "films");

        model.addAttribute("filmsList", filmService.getAllFilms());

        return new ModelAndView("admin/film/films");
    }

    @GetMapping("/editFilm/{id}")
    public ModelAndView editFilm(Model model, @PathVariable String id) {
        Film film = filmService.getFilm(Long.parseLong(id));
        model.addAttribute("title", "Редактирование фильма " + film.getName());
        model.addAttribute("pageActive", "Films");
        model.addAttribute("film", film);
        List<TypeFilm> filmType = List.of(TypeFilm.Twodimensional, TypeFilm.Threedimensional, TypeFilm.IMAX);
        model.addAttribute("typeFilm", filmType);
        return new ModelAndView("admin/film/editFilm");

    }

    @PostMapping("/editFilm/{id}")
    public ModelAndView editFilm(@ModelAttribute Film film,
                                      @PathVariable String id) {

        filmService.updateFilm(film);
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

    @PostMapping("/editFilm/editMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> editMainImageFilm(@RequestPart("file") MultipartFile file,
                                                    @PathVariable Long id) {

        Film film = filmService.getFilm(id);
        film.setMainImage(uploadFile.uploadFile(file, film.getMainImage()));
        filmService.saveFilm(film);
        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editFilm/deleteMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImageFilm(Model model, @PathVariable long id) {
        Film film = filmService.getFilm(id);
        uploadFile.deleteFile(film.getMainImage());
        film.setMainImage(null);
        filmService.saveFilm(film);
        return ResponseEntity.ok("Файл успешно удален");
    }

    @GetMapping("/editFilm/showAllImages/{id}")
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
