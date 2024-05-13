package spacelab.kinocms.validator;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.SessionDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionValidator {

    public List<String> validate(List<SessionDto> sessions) {
        List<SessionValid> sessionDtosWithErrors = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for (SessionDto session : sessions) {
            SessionValid sessionValid = new SessionValid();
            sessionValid.setId(Long.valueOf(session.getId()));
            if (session.getTimeSession() == null || session.getTimeSession().isEmpty()) {
                sessionValid.setTimeSession(false);
            }
            if (session.getFilmId() == null || session.getFilmId().isEmpty()) {
                sessionValid.setFilmId(false);
            }
            if (session.getCinemaId() == null || session.getCinemaId().isEmpty()) {
                sessionValid.setCinemaId(false);
            }
            if (session.getHallId() == null || session.getHallId().isEmpty()) {
                sessionValid.setHallId(false);
            }
            if (session.getPrice() < 0) {
                sessionValid.setPrice(false);
            }
            if (session.getPriceVip() < 0) {
                sessionValid.setPriceVip(false);
            }
            if (!sessionValid.getTimeSession()
                    || !sessionValid.getFilmId()
                    || !sessionValid.getCinemaId()
                    || !sessionValid.getHallId()
                    || !sessionValid.getPrice()
                    || !sessionValid.getPriceVip()) {
                sessionDtosWithErrors.add(sessionValid);
            }


        }

        for (SessionValid item : sessionDtosWithErrors) {
            if (!item.getTimeSession()) {
                errors.add("timeSession"+item.getId());
            }
            if (!item.getFilmId()) {
                errors.add("filmId"+item.getId());
            }
            if (!item.getCinemaId()) {
                errors.add("cinemaId"+item.getId());
            }
            if (!item.getHallId()) {
                errors.add("hallId"+item.getId());
            }
            if (!item.getPrice()) {
                errors.add("price"+item.getId());
            }
            if (!item.getPriceVip()) {
                errors.add("priceVip"+item.getId());
            }

        }

        return errors;
    }
}