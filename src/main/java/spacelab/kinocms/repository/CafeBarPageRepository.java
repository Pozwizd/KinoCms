package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.page.CafeBarPage;

public interface CafeBarPageRepository extends JpaRepository<CafeBarPage, Long> {
}