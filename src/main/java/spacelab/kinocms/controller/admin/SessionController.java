package spacelab.kinocms.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.BannerForNewsAndStocksItemDto;
import spacelab.kinocms.Dto.SessionDto;
import spacelab.kinocms.Dto.SessionPageDto;
import spacelab.kinocms.Mapper.SessionMapper;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.Session;
import spacelab.kinocms.model.banners.BannerForNewsAndStocks;
import spacelab.kinocms.service.CinemaService;
import spacelab.kinocms.service.FilmService;
import spacelab.kinocms.service.HallService;
import spacelab.kinocms.service.SessionService;
import spacelab.kinocms.validator.SessionValid;
import spacelab.kinocms.validator.SessionValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/session")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final FilmService filmService;
    private final CinemaService cinemaService;
    private final HallService hallService;
    private final SessionValidator sessionValidator;

    private final SessionMapper sessionMapper;

    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Сеансы");
        return new ModelAndView("admin/session");
    }

    @GetMapping("/getSessionPageDto")
    @ResponseBody
    public SessionPageDto getSession() {

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

    @GetMapping("/add/")
    public ModelAndView addSession() {
        Session session = new Session();
        session.setCinemaId(cinemaService.getCinema(1L));
        session.setTimeSession(LocalDateTime.now());

        sessionService.createSession(session);
        return new ModelAndView("redirect:/admin/session");
    }

    @PostMapping("/saveSessions")
    @ResponseBody
    public ResponseEntity<String> saveSessions(@RequestBody List<SessionDto> sessions) {

        // Удалить сессии которых нет из базы данных
        List<Long> ids = sessions.stream().map(SessionDto::getId).map(Long::parseLong).collect(Collectors.toList());

        List<Session> sessionList = sessionService.getAllSession();
        sessionList.stream().filter(session -> !ids.contains(session.getId()))
                .forEach(sessionService::deleteSession);



        List<String> sessionDtosWithErrors = sessionValidator.validate(sessions);

        if (!sessionDtosWithErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(sessionDtosWithErrors.toString());
        }

        for (SessionDto item : sessions) {
            Session session = sessionMapper.toEntity(item);
            sessionService.createSession(session);
        }

        return ResponseEntity.ok("ok");
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public ModelAndView deleteSession(@PathVariable String id) {
        sessionService.deleteSession(sessionService.readSession(Long.parseLong(id)));
        return new ModelAndView("redirect:/admin/session");
    }

}
