package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.page.MainPage;

import java.sql.Date;

@Repository
public interface MainPageRepository extends JpaRepository<MainPage, Long> {
    @Query("SELECT m.dateOfCreated FROM MainPage m WHERE m.id = :mainPageId")
    Date findCreationDateById(@Param("mainPageId") Long mainPageId);

    @Query("SELECT m.name FROM MainPage m WHERE m.id = :mainPageId")
    String findNameById(@Param("mainPageId") Long mainPageId);
}