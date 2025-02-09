package spacelab.kinocms.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import spacelab.kinocms.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public void saveUser(User user);
    public User getUser(Long id);
    public List<User> getAllUsers();
    public void updateUser(User user);
    public void deleteUser(User user);

    Page<User> findAllUsers(int page, int pageSize);

    Page<User> findUsersByRequest(int page, int pageSize, String search);

    User getUserByEmail(String name);

    Optional<User> findByUsername(String mail);
}
