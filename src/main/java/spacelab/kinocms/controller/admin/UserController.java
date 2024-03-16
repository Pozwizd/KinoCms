package spacelab.kinocms.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.UserDto;
import spacelab.kinocms.Mapper.UserMapper;
import spacelab.kinocms.model.User;
import spacelab.kinocms.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private AuthenticationManager authenticationManager;
    private final int pageSize = 1;


    @GetMapping({"/", ""})
    public ModelAndView viewUsers(Model model) {
        model.addAttribute("title", "Пользователи");
        model.addAttribute("pageActive", "users");
        return new ModelAndView("admin/user/usersPage");
    }


    @GetMapping("/{userId}")
    public ModelAndView viewUserDetails(@PathVariable Long userId, Model model) {
        UserDto user = userMapper.toDto(userService.getUser(userId));
        model.addAttribute("title", "Пользователи");
        model.addAttribute("pageActive", "users");
        model.addAttribute("user", user);
        return new ModelAndView("admin/user/userEdit");
    }

    @PostMapping("/{userId}")
    public ModelAndView editUserDetails(@ModelAttribute @Valid UserDto user,
                                        @RequestParam("confirmPassword") String confirmPassword,
                                        BindingResult result, Model model, @PathVariable Long  userId) {

        // Проверка ошибок валидации
        if (result.hasErrors()) {
            model.addAttribute("title", "Пользователи");
            model.addAttribute("pageActive", "users");
            model.addAttribute("user", user);
            return new ModelAndView("redirect:/admin/users/" + userId);
        }

        user.setId(userId);
        userService.saveUser(userMapper.toEntity(user));

        return new ModelAndView("redirect:/admin/users");
    }


    @GetMapping("/getPage")
    public @ResponseBody Page<User> getUsersOnPage(@RequestParam int page) {
        return userService.findAllUsers(page, pageSize);
    }



    @GetMapping("/getPageSearch")
    public @ResponseBody Page<User> getUsersPageBySearch(@RequestParam int page, @RequestParam String search) {
        return userService.findUsersByRequest(page, pageSize, search);
    }
}
