package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;

import java.util.List;

@Repository
public interface ImageNewsRepository extends JpaRepository<ImageNews, Long> {
    List<ImageNews> findAllByNews(News news);
}