package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.entity.page.ContactCinema;

@Repository
public interface ContactCinemaRepository extends JpaRepository<ContactCinema, Long> {
}