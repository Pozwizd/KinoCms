package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.page.ContactPage;

public interface ContactPageRepository extends JpaRepository<ContactPage, Long> {
}