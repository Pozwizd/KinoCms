package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
}