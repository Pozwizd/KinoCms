package spacelab.kinocms.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.Dto.SchedulePageDto;
import spacelab.kinocms.model.Schedule;
import spacelab.kinocms.service.CinemaService;
import spacelab.kinocms.service.FilmService;
import spacelab.kinocms.service.HallService;
import spacelab.kinocms.service.ScheduleService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("admin/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final FilmService filmService;
    private final CinemaService cinemaService;
    private final HallService hallService;

    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {

        model.addAttribute("films", filmService.getAllFilms());
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("halls", hallService.getAllHall());
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        Schedule schedule = scheduleService.readSchedule(1L);

        if (schedule != null) {
            model.addAttribute("schedule", schedule);
        }

        return new ModelAndView("admin/schedule");
    }

    @PostMapping("/save")
    public ModelAndView saveSchedule(@RequestParam(name="id[]") List<Long> id,
                                     @RequestParam(name="timeSession[]") List<LocalDateTime> timeSession,
                                     @RequestParam(name="filmId[]") List<Long> filmId,
                                     @RequestParam(name="cinemaId[]") List<Long> cinemaId,
                                     @RequestParam(name="hallId[]") List<Long> hallId,
                                     @RequestParam(name="price[]") List<Double> price,
                                     @RequestParam(name="priceVip[]") List<Double> priceVip) {


        for (int i = 0; i < id.size(); i++) {
            Schedule schedule = scheduleService.readSchedule(id.get(i));
            if (schedule == null) {
                schedule = new Schedule();
            }
            schedule.setId(id.get(i));
            schedule.setTimeSession(timeSession.get(i));
            schedule.setFilmId(filmService.getFilm(filmId.get(i)));
            schedule.setCinemaId(cinemaService.getCinema(cinemaId.get(i)));
            schedule.setHallId(hallService.getHall(hallId.get(i)));
            schedule.setPrice(price.get(i));
            schedule.setPriceVip(priceVip.get(i));
            scheduleService.createSchedule(schedule);
        }

        return new ModelAndView("redirect:/admin/schedule/");
    }




//    @ResponseBody
//    public ResponseEntity<String> saveSchedule(@RequestBody ScheduleDtoPage scheduleDto) {
//
//
//        System.out.println(scheduleDto);
//        System.out.println("Schedule saved");
//
//        return ResponseEntity.ok("ok");
//    }

    // Ajax

    @GetMapping("/getSchedulePageDto")
    @ResponseBody
    public SchedulePageDto getSchedule() {

        SchedulePageDto schedules = new SchedulePageDto();
        schedules.setFilms(filmService.getAllFilms());
        schedules.setCinemas(cinemaService.getAllCinemas());
        schedules.setHalls(hallService.getAllHall());
        schedules.setSchedules(scheduleService.getAllSchedules());

        return schedules;
    }




}
