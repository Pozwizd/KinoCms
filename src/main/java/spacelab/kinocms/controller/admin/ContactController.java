package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.page.ContactCinema;
import spacelab.kinocms.model.page.ContactPage;
import spacelab.kinocms.service.ContactService;

import java.util.List;

@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("admin/pages/contactPage/")
    public ModelAndView showContactPage(Model model) {

        ContactPage contactPage = contactService.getContactPage();
        List<ContactCinema> contactCinema = contactService.getContactPage().getContactCinemas();

        model.addAttribute("contactPage",contactPage);
        model.addAttribute("contacts", contactCinema);
        return new ModelAndView("admin/page/editContactPage");
    }

    @GetMapping("admin/pages/contactPage/showAllContact")
    @ResponseBody
    public List<ContactCinema> showAllContact() {
        ContactPage contactPage = contactService.getContactPage();
        return contactService.getContactPage().getContactCinemas();
    }

    @PostMapping("admin/pages/contactPage/")
    public ModelAndView contact(@ModelAttribute ContactPage contactPage,
                                @RequestParam("logoImage[]") List<MultipartFile> logoImage,
                                HttpServletRequest request) {

        contactService.saveContactPage(contactPage, logoImage);

        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

}
