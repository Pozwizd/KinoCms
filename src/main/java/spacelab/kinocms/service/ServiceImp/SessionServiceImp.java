package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.Session;
import spacelab.kinocms.repository.SessionRepository;
import spacelab.kinocms.service.SessionService;
import spacelab.kinocms.specification.SessionSpecification;


import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class SessionServiceImp implements SessionService {

    private SessionRepository sessionRepository;


    @Override
    public void createSession(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public Session readSession(long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Session> getAllSession() {
        return sessionRepository.findAll();
    }




    @Override
    public void updateSession(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(long id) {
        sessionRepository.deleteById(id);
    }

    @Override
    public List<Session> getAllSessionByRequest(Date dateSession,
                                                Film film,
                                                Cinema cinema,
                                                Hall hall,
                                                Boolean d3d,
                                                Boolean d2d,
                                                Boolean imax) {
        Specification<Session> spec = new SessionSpecification(dateSession,
                film,
                cinema,
                hall,
                d3d,
                d2d,
                imax);

        return sessionRepository.findAll(spec);
    }
}
