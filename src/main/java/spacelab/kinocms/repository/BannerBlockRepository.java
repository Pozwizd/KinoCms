package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.banners.BannerBlock;

@Repository
public interface BannerBlockRepository extends JpaRepository<BannerBlock, Long> {
}