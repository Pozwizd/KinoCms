package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;

import java.util.List;

public interface ImageNewsRepository extends JpaRepository<ImageNews, Long> {
    List<ImageNews> findAllByNews(News news);
}