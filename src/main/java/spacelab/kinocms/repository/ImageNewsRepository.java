package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.ImagesEntity.ImageNews;
import spacelab.kinocms.entity.News;

import java.util.List;

@Repository
public interface ImageNewsRepository extends JpaRepository<ImageNews, Long> {
    List<ImageNews> findAllByNews(News news);
    ImageNews findTopImageNewsByNewsOrderByIdDesc(News news);
}