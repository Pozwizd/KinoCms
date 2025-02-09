package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.banners.BannerBackground;

@Repository
public interface BannerBackgroundRepository extends JpaRepository<BannerBackground, Long> {
}