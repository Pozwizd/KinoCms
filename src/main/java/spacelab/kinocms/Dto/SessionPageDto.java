package spacelab.kinocms.Dto;

import lombok.Data;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.Session;

import java.util.List;

@Data
public class SessionPageDto {

    private List<Session> sessions;
    private List<Film> films;
    private List<Cinema> cinemas;
    private List<Hall> halls;


}
