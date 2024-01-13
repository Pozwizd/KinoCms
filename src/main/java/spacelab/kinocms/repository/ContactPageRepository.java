package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.page.ContactPage;

@Repository
public interface ContactPageRepository extends JpaRepository<ContactPage, Long> {
}