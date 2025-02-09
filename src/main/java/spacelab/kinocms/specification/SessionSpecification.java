package spacelab.kinocms.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.entity.Film;
import spacelab.kinocms.entity.Hall;
import spacelab.kinocms.entity.Session;

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

        if (d3d != null && d3d) {
            Join<Session, Film> filmJoin = root.join("filmId", JoinType.LEFT);
            Predicate typePredicate = cb.isMember(TypeFilm.Threedimensional, filmJoin.get("typeFilm"));
            predicates.add(typePredicate);
        }

        if (d2d != null && d2d) {
            Join<Session, Film> filmJoin = root.join("filmId", JoinType.LEFT);
            Predicate typePredicate = cb.isMember(TypeFilm.Twodimensional, filmJoin.get("typeFilm"));
            predicates.add(typePredicate);
        }

        if (imax != null && imax) {
            Join<Session, Film> filmJoin = root.join("filmId", JoinType.LEFT);
            Predicate typePredicate = cb.isMember(TypeFilm.IMAX, filmJoin.get("typeFilm"));
            predicates.add(typePredicate);
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