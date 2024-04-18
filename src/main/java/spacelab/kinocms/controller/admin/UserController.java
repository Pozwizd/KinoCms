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
    private final int pageSize = 5;


    @GetMapping({"/", ""})
    public ModelAndView viewUsers(Model model) {
        model.addAttribute("title", "Пользователи");
        model.addAttribute("pageActive", "users");
        return new ModelAndView("admin/user/usersPage");
    }


    @GetMapping("/{userId}")
    public ModelAndView viewUserDetails(@PathVariable Long userId, Model model) {
        UserDto userDto = userMapper.toDto(userService.getUser(userId));
        model.addAttribute("title", "Пользователи");
        model.addAttribute("pageActive", "users");
        model.addAttribute("userDto", userDto);
        return new ModelAndView("admin/user/userEdit");
    }

    @PostMapping("/{userId}")
    public ModelAndView editUserDetails(@Valid @ModelAttribute("userDto") UserDto userDto,
                                  BindingResult bindingResult,
                                  @PathVariable Long userId) {


        if (bindingResult.hasErrors()||
                (userService.getUserByEmail(userDto.getEmail()) != null)
        && (!userService.getUserByEmail(userDto.getEmail()).getId().equals(userId))) {
            return new ModelAndView("admin/user/userEdit");
        }
        userDto.setId(userId);
        User user = userMapper.toEntity(userDto);
        user.setId(userId);
        userService.saveUser(user);


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
