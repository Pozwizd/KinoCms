package spacelab.kinocms.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.SessionPageDto;
import spacelab.kinocms.Mapper.SessionMapper;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.Session;
import spacelab.kinocms.service.CinemaService;
import spacelab.kinocms.service.FilmService;
import spacelab.kinocms.service.HallService;
import spacelab.kinocms.service.SessionService;

import java.sql.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/session")
public class SessionFilmController {

    private final SessionService sessionService;
    private final FilmService filmService;
    private final CinemaService cinemaService;
    private final HallService hallService;

    private final SessionMapper sessionMapper;

    @GetMapping({"/", ""})
    public ModelAndView session(Model model) {
        model.addAttribute("title", "Сеансы");
        return new ModelAndView("user/session");
    }

    @GetMapping("/getSessionPageDto")
    @ResponseBody
    public SessionPageDto getFormSelection() {

        SessionPageDto sessionPageDto = new SessionPageDto();
        sessionPageDto.setFilms(filmService.getAllFilms());
        sessionPageDto.setCinemas(cinemaService.getAllCinemas());
        sessionPageDto.setHalls(hallService.getAllHall());
        sessionPageDto.setSessions(sessionService.getAllSession());
        return sessionPageDto;
    }

    @GetMapping("/getHallsByCinema/{id}")
    @ResponseBody
    public List<Hall> getHallId(@PathVariable String id) {

        return hallService.getHallByCinema(cinemaService.getCinema(Long.parseLong(id)));
    }

    @GetMapping("/getAllSession")
    @ResponseBody
    public List<Session> getAllSession() {
        return sessionService.getAllSession();
    }


    @GetMapping("/getPageSearch")
    @ResponseBody
    public List<Session> getSessionsBySearch(
            @RequestParam(value = "dateSession", required = false) Date dateSession,
            @RequestParam(value = "films", required = false) String films,
            @RequestParam(value = "cinemas", required = false) String cinemas,
            @RequestParam(value = "halls", required = false) String halls,
            @RequestParam(value = "imax", required = false) Boolean imax,
            @RequestParam(value = "3d", required = false) Boolean d3d,
            @RequestParam(value = "2d", required = false) Boolean d2d) {

        Film film = films == null || films.isEmpty() ? null
                : filmService.getFilm(Long.parseLong(films));
        Cinema cinema = cinemas == null || cinemas.isEmpty() ? null
                : cinemaService.getCinema(Long.parseLong(cinemas));
        Hall hall;
        if (halls == null || halls.isEmpty() || halls.equals("null")) {
            hall = null;
        } else {
            hall = hallService.getHall(Long.parseLong(halls));
        }

        List<Session> sessions = sessionService.getAllSessionByRequest(dateSession,
                film,
                cinema,
                hall,d3d,d2d,imax);
        return sessions;
    }
}
