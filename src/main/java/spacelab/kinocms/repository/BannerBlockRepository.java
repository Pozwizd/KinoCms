package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.BannerBlock;

public interface BannerBlockRepository extends JpaRepository<BannerBlock, Long> {
}