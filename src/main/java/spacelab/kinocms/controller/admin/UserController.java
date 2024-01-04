package spacelab.kinocms.controller.admin;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.User;
import spacelab.kinocms.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final int pageSize = 1;


    @GetMapping({"/", ""})
    public ModelAndView viewUsers(Model model) {
        model.addAttribute("title", "Пользователи");
        model.addAttribute("pageActive", "users");
        return new ModelAndView("admin/usersPage");
    }



    @GetMapping("/{userId}")
    public ModelAndView viewUserDetails(@PathVariable Long userId) {
        return new ModelAndView("admin/userEdit");
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
