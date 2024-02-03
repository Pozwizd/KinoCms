package spacelab.kinocms.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.Film;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("SELECT f FROM Film f WHERE (CURRENT_DATE BETWEEN f.startPremiereDate AND f.endPremiereDate)")
    List<Film> getAllCurrentFilm();


    List<Film> findByStartPremiereDateAfter(Date currentDate);


}