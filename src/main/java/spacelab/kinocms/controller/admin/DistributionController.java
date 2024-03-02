package spacelab.kinocms.controller.admin;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.MailService;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.MailTemplate;
import spacelab.kinocms.model.User;
import spacelab.kinocms.service.MailTemplateService;
import spacelab.kinocms.service.UserService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/distribution")
@AllArgsConstructor
public class DistributionController {

    private final MailService mailService;
    private final UserService userService;
    private final MailTemplateService mailTemplateService;
    private final UploadFile uploadFile;

    private JavaMailSender mailSender;

    @GetMapping({"/", ""})
    public ModelAndView index(Model model, HttpSession session) {
        model.addAttribute("title", "Рассылка");
        model.addAttribute("pageActive", "distribution");
        List<User> users = (List<User>) session.getAttribute("userList");
        if (users == null){
            model.addAttribute("choiceUser", true);
        } else {
            model.addAttribute("choiceUser", false);
            session.setAttribute("userList", users);
        }
        return new ModelAndView("admin/mailing/distribution");
    }

    @PostMapping("/sendMail")
    public ModelAndView sendMail(@RequestParam("choiceUsers") Boolean choiceUsers,
                                 @RequestParam("selectedMailTemplate") String selectedMailTemplate,
                                 HttpSession session) throws MessagingException, IOException {
        List<User> users = (List<User>) session.getAttribute("userList");

        String template = mailTemplateService.getMailTemplate(Long.valueOf(selectedMailTemplate)).getUrl();

        if (choiceUsers) {
            for (User user : userService.getAllUsers()) {
                mailService.sendHtmlEmail(user.getEmail(), template);
            }
        } else {
            for (User user : users) {
                mailService.sendHtmlEmail(user.getEmail(), template);
            }
        }
        session.removeAttribute("userList");

        return new ModelAndView("redirect:/admin/mailing/distribution/");
    }

    @GetMapping("/userMailing")
    public ModelAndView getMailUser(Model model) {
        model.addAttribute("title", "Рассылка");
        model.addAttribute("pageActive", "distribution");
        model.addAttribute("userList", userService.getAllUsers());
        return new ModelAndView("admin/mailing/userMailing");
    }

    @GetMapping("/getPageSearch")
    public ModelAndView getUsersPageBySearch(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam String search, Model model) {
        model.addAttribute("title", "Рассылка");
        model.addAttribute("pageActive", "distribution");
        model.addAttribute("userList",
                userService.findUsersByRequest(0, 99, search));
        return new ModelAndView("admin/mailing/userMailing");
    }

    @PostMapping("/userMailing")
    public ModelAndView giveMailUser(@RequestParam (value = "user", required = false) List<User> users, Model model,
                                     HttpServletRequest request, HttpSession session) {

        System.out.println(users);
        session.setAttribute("userList", users);

        return new ModelAndView("redirect:/admin/distribution");
    }

//   Ajax ====================

    @PostMapping("/addMailTemplate/")
    @ResponseBody
    public ResponseEntity<String> addMailTemplate(@RequestPart("file") MultipartFile file) {
        List<MailTemplate> mailTemplates = mailTemplateService.getAllMailTemplateDecs();
        String response = "Файл успешно загружен";
        if (mailTemplates.size() > 4) {
            response = "2";
            mailTemplateService.deleteMailTemplate(mailTemplates.get(mailTemplates.size()-1).getId());
        }
        MailTemplate mailTemplate = new MailTemplate();
        mailTemplate.setName(file.getOriginalFilename());
        mailTemplate.setUrl(uploadFile.uploadMailTemplate(file, mailTemplate.getUrl()));

        mailTemplateService.saveMailTemplate(mailTemplate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getMailTemplate/")
    @ResponseBody
    public MailTemplate getMailTemplate() {

        return mailTemplateService.getLastMailTemplate();
    }

    @GetMapping("/getAllMailTemplate/")
    @ResponseBody
    public List<MailTemplate> getAllMailTemplate() {
        return mailTemplateService.getAllMailTemplateDecs();
    }

    @PostMapping("/deleteMailTemplate/{id}")
    @ResponseBody
    public ResponseEntity<String>  deleteMailTemplate(@PathVariable String id) {

        uploadFile.deleteFile(mailTemplateService.getMailTemplate(Long.valueOf(id)).getUrl());
        mailTemplateService.deleteMailTemplate(Long.valueOf(id));

        return ResponseEntity.ok("Файл успешно удален");
    }



}
