package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.model.page.Page;

import java.util.List;

@Repository
public interface ImagePageRepository extends JpaRepository<ImagePage, Long> {
    @Query("SELECT i FROM ImagePage i ORDER BY i.id DESC")
    ImagePage findLatestImagePage();


    List<ImagePage> findAllByPage(Page page);

    void deleteAllByPage(Page page);
}