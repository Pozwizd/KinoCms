package spacelab.kinocms.Dto;

import lombok.Data;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.entity.Film;
import spacelab.kinocms.entity.Hall;
import spacelab.kinocms.entity.Session;

import java.util.List;

@Data
public class SessionPageDto {

    private List<Session> sessions;
    private List<Film> films;
    private List<Cinema> cinemas;
    private List<Hall> halls;


}
