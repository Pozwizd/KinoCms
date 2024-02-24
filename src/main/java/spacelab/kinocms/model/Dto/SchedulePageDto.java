package spacelab.kinocms.model.Dto;

import lombok.Data;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.Schedule;

import java.util.ArrayList;
import java.util.List;

@Data
public class SchedulePageDto {

    private List<Schedule> schedules;
    private List<Film> films;
    private List<Cinema> cinemas;
    private List<Hall> halls;


}
