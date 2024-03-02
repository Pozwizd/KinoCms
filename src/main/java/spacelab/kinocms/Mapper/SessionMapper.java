package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.SessionDto;
import spacelab.kinocms.model.Session;
import spacelab.kinocms.service.CinemaService;
import spacelab.kinocms.service.FilmService;
import spacelab.kinocms.service.HallService;
import spacelab.kinocms.service.SessionService;

import java.time.LocalDateTime;

@Service
public class SessionMapper {
    private final SessionService sessionService;
    private final FilmService filmService;
    private final CinemaService cinemaService;
    private final HallService hallService;

    public SessionMapper(SessionService sessionService, FilmService filmService, CinemaService cinemaService, HallService hallService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.cinemaService = cinemaService;
        this.hallService = hallService;
    }

    public Session toEntity(SessionDto sessionDto) {
        Session session = sessionService.readSession(Long.valueOf(sessionDto.getId()));
        if ((session == null)) {
            session = new Session();
        }
        session.setId(Long.valueOf(sessionDto.getId()));

        if (sessionDto.getTimeSession() == null || sessionDto.getTimeSession().isEmpty()) {
            session.setTimeSession(LocalDateTime.now());
        } else {
            session.setTimeSession(LocalDateTime.parse(sessionDto.getTimeSession()));
        }

        if (sessionDto.getFilmId() == null || sessionDto.getFilmId().isEmpty()) {
            session.setFilmId(null);
        } else {
            session.setFilmId(filmService.getFilm(Long.valueOf(sessionDto.getFilmId())));
        }
        if (sessionDto.getCinemaId() == null || sessionDto.getCinemaId().isEmpty()) {
            session.setCinemaId(null);
        } else {
            session.setCinemaId(cinemaService.getCinema(Long.valueOf(sessionDto.getCinemaId())));
        }
        if (sessionDto.getHallId() == null || sessionDto.getHallId().isEmpty()) {
            session.setHallId(null);
        } else {
            session.setHallId(hallService.getHall(Long.valueOf(sessionDto.getHallId())));
        }

        if (sessionDto.getPrice() == null || sessionDto.getPrice().isEmpty()) {
            session.setPrice((double) 0);
        } else {
            session.setPrice(Double.valueOf(sessionDto.getPrice()));
        }

        if (sessionDto.getPriceVip() == null || sessionDto.getPriceVip().isEmpty()) {
            session.setPriceVip((double) 0);
        } else {
            session.setPriceVip(Double.valueOf(sessionDto.getPriceVip()));
        }

        return session;
    }

    public SessionDto toDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(String.valueOf(session.getId()));
        sessionDto.setTimeSession(String.valueOf(session.getTimeSession()));
        sessionDto.setFilmId(String.valueOf(session.getFilmId().getId()));
        sessionDto.setCinemaId(String.valueOf(session.getCinemaId().getId()));
        sessionDto.setHallId(String.valueOf(session.getHallId().getId()));
        sessionDto.setPrice(String.valueOf(session.getPrice()));
        sessionDto.setPriceVip(String.valueOf(session.getPriceVip()));
        return sessionDto;
    }
}
