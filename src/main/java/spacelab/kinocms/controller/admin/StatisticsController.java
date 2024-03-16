package spacelab.kinocms.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.service.FilmService;

@Controller
@RequestMapping( "/admin")
@AllArgsConstructor
public class StatisticsController {
    private final FilmService filmService;


    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Статистика");
        model.addAttribute("pageActive", "statistics");
        int[] countFilms = filmService.countFilmsMonth();
        model.addAttribute("sessionStatistics", convertToJson(countFilms));


        return new ModelAndView("admin/statistics");
    }

    private String convertToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
