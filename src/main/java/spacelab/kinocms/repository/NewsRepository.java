package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}