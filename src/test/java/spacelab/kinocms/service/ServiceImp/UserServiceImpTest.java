package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import spacelab.kinocms.entity.User;
import spacelab.kinocms.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @BeforeEach
    public void setUp() {
        reset(userRepository);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        userService.saveUser(user);
        verify(userRepository).save(user);
    }

    @Test
    void testGetUser() {
        Long userId = 1L;
        User expectedUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));
        User actualUser = userService.getUser(userId);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetAllUsers() {
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getAllUsers();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        userService.updateUser(user);
        verify(userRepository).save(user);
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        userService.deleteUser(user);
        verify(userRepository).delete(user);
    }

    @Test
    void testFindAllUsers() {
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        List<User> users = Arrays.asList(new User(), new User());
        Page<User> expectedPage = new PageImpl<>(users, pageable, users.size());
        when(userRepository.findAll(pageable)).thenReturn(expectedPage);
        Page<User> actualPage = userService.findAllUsers(page, pageSize);
        assertEquals(expectedPage, actualPage);
    }

    @Test
    void testFindUsersByRequest() {
        int page = 0;
        int pageSize = 10;
        String search = "test";
        Pageable pageable = PageRequest.of(page, pageSize);
        List<User> users = Arrays.asList(new User(), new User());
        Page<User> expectedPage = new PageImpl<>(users, pageable, users.size());
        when(userRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(expectedPage);
        Page<User> actualPage = userService.findUsersByRequest(page, pageSize, search);
        assertEquals(expectedPage, actualPage);
        verify(userRepository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void testLoadUserByUsername() {
        String email = "test@example.com";
        User expectedUser = new User();
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(expectedUser));
        UserDetails actualUserDetails = userService.loadUserByUsername(email);
        assertEquals(expectedUser, actualUserDetails);
    }
}