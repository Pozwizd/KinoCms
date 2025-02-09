package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.entity.Film;
import spacelab.kinocms.entity.Hall;
import spacelab.kinocms.entity.Session;
import spacelab.kinocms.repository.SessionRepository;
import spacelab.kinocms.service.SessionService;
import spacelab.kinocms.specification.SessionSpecification;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class SessionServiceImp implements SessionService {



    private SessionRepository sessionRepository;
    private static final Logger logger = LogManager.getLogger(SessionServiceImp.class);

    @Override
    public void createSession(Session session) {
        logger.info("Create session: " + session);
        sessionRepository.save(session);
    }

    @Override
    public Session readSession(long id) {
        logger.info("Get session by id: " + id);
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Session> getAllSession() {
        logger.info("Get all session");
        return sessionRepository.findAll();
    }

    @Override
    public void updateSession(Session session) {
        logger.info("Update session: {}", session);
        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Session session) {
        logger.info("Delete session: {}", session);

        sessionRepository.delete(session);
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

        logger.info("Get all session by request: " + spec);
        return sessionRepository.findAll(spec);
    }
}
