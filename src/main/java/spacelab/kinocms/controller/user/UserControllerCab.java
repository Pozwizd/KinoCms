package spacelab.kinocms.controller.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.UserDto;
import spacelab.kinocms.Mapper.UserMapper;
import spacelab.kinocms.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/cabinet")
@AllArgsConstructor
public class UserControllerCab {

    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping({"","/"})
    public ModelAndView viewUserDetails(Model model, Principal principal) {
        UserDto user = userMapper.toDto(userService.getUserByEmail(principal.getName()));
        model.addAttribute("title", "Личный кабинет");
        model.addAttribute("pageActive", "users");
        model.addAttribute("user", user);
        return new ModelAndView("/user/cabinet");
    }

    @PostMapping("/{userId}")
    public ModelAndView editUserDetails(@ModelAttribute @Valid UserDto user,
                                        BindingResult result, Model model, @PathVariable Long  userId) {

        // Проверка ошибок валидации
        if (result.hasErrors()) {
            model.addAttribute("title", "Пользователи");
            model.addAttribute("pageActive", "users");
            model.addAttribute("user", user);
            return new ModelAndView("redirect:/cabinet/" + userId);
        }

        user.setId(userId);
        userService.saveUser(userMapper.toEntity(user));

        return new ModelAndView("redirect:/cabinet/" + userId);
    }
}
