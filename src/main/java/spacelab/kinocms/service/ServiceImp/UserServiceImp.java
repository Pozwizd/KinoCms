package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.User;
import spacelab.kinocms.repository.UserRepository;
import spacelab.kinocms.service.UserService;
import spacelab.kinocms.specification.UserSpecification;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("UserId " + id + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public Page<User> findAllUsers(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findUsersByRequest(int page, int pageSize, String search) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Specification<User> specification = new UserSpecification(search);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username " + email + " not found"));
    }
}
