package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.Session;

import java.sql.Date;
import java.util.List;

@Service
public interface SessionService {

    // CRUD Schedule

    public void createSession(Session session);

    public Session readSession(long id);

    public List<Session> getAllSession();


    public void updateSession(Session session);

    public void deleteSession(Session session);

    List<Session> getAllSessionByRequest(Date dateSession, Film film, Cinema cinema, Hall hall, Boolean d3d, Boolean d2d, Boolean imax);
}
