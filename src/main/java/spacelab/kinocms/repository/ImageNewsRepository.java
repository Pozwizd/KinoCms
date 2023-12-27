package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.ImagesEntity.ImageNews;

public interface ImageNewsRepository extends JpaRepository<ImageNews, Long> {
}