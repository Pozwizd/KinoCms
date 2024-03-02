package spacelab.kinocms.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.Session;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionSpecification implements Specification<Session> {

    private final Date dateSession;
    private final Film film;
    private final Cinema cinema;
    private final Hall hall;

    private final Boolean d3d;
    private final Boolean d2d;
    private final Boolean imax;

    public SessionSpecification(Date dateSession, Film film, Cinema cinema, Hall hall, Boolean d3d, Boolean d2d, Boolean imax) {
        this.dateSession = dateSession;
        this.film = film;
        this.cinema = cinema;
        this.hall = hall;
        this.d3d = d3d;
        this.d2d = d2d;
        this.imax = imax;
    }

    @Override
    public Predicate toPredicate(Root<Session> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (dateSession != null) {
            predicates.add(cb.equal(root.get("timeSession").as(LocalDate.class), dateSession.toLocalDate()));
        }
        if (film != null) {
            predicates.add(cb.equal(root.get("filmId"), film));
        }

        if (cinema != null) {
            predicates.add(cb.equal(root.get("cinemaId"), cinema));
        }
        if (hall != null) {
            predicates.add(cb.equal(root.get("hallId"), hall));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}