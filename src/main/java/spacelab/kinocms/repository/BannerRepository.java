package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}