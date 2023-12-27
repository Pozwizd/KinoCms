package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.page.User;

public interface UserRepository extends JpaRepository<User, Long> {
}