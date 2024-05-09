package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spacelab.kinocms.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT MAX(n.id) FROM News n")
    Long idLastFilm();
}