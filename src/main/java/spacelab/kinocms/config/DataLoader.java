package spacelab.kinocms.config;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spacelab.kinocms.entity.Role;
import spacelab.kinocms.entity.User;
import spacelab.kinocms.enums.Lanquage;
import spacelab.kinocms.service.UserService;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final Faker faker;
    private final UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadUsers() {
        if (userService.findByUsername("admin@gmail.com").isEmpty()) {
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@gmail.com");
            user.setPassword("admin");
            user.setLanguage(Lanquage.Русский);
            user.setRole(Role.ADMIN);

            userService.saveUser(user);
        }



    }
}
