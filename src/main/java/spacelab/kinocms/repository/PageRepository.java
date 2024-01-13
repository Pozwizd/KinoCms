package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.page.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long>, JpaSpecificationExecutor<Page> {
}