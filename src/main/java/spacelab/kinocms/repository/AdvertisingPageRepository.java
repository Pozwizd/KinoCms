package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.page.AdvertisingPage;

public interface AdvertisingPageRepository extends JpaRepository<AdvertisingPage, Long> {
}