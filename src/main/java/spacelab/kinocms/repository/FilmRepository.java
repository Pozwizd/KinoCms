package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.Film;

import java.sql.Date;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    // Все фильмы премьера которых началась
    @Query("SELECT f FROM Film f WHERE ( f.startPremiereDate < CURRENT_DATE())")
    List<Film> getAllCurrentFilm();


    List<Film> findByStartPremiereDateAfter(Date currentDate);

    @Query("SELECT COUNT(f) FROM Film f " +
            "WHERE FUNCTION('YEAR', f.startPremiereDate) = :year " +
            "AND FUNCTION('MONTH', f.startPremiereDate) = :month")
    int countFilmsInMonth(int year, int month);

    @Query("SELECT MAX(f.id) FROM Film f")
    Long idLastFilm();
}