package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.page.MainPage;

@Repository
public interface MainPageRepository extends JpaRepository<MainPage, Long> {
}