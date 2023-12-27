package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.page.MainPage;

public interface MainPageRepository extends JpaRepository<MainPage, Long> {
}