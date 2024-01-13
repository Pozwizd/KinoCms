package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.model.page.Page;

import java.util.List;

@Repository
public interface ImagePageRepository extends JpaRepository<ImagePage, Long> {
    @Query("select i from ImagePage i order by i.id desc")
    ImagePage findLastImagePage();

    List<ImagePage> findAllByPage(Page page);

    void deleteAllByPage(Page page);
}