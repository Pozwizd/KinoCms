package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.page.ContactCinema;
import spacelab.kinocms.model.page.ContactPage;
import spacelab.kinocms.Dto.Page.ContactPageDto;
import spacelab.kinocms.service.ContactCinemaService;
import spacelab.kinocms.service.ContactService;

import java.util.List;

@Controller
public class ContactController {

    private final ContactService contactService;

    private final ContactCinemaService contactCinemaService;

    public ContactController(ContactService contactService, ContactCinemaService contactCinemaService) {
        this.contactService = contactService;
        this.contactCinemaService = contactCinemaService;
    }

    @GetMapping("admin/pages/contactPage/")
    public ModelAndView showContactPage(Model model) {

        ContactPage contactPage = contactService.getContactPage();
        List<ContactCinema> contactCinema = contactService.getContactPage().getContactCinemas();
        model.addAttribute("title", "Редактирование страницы " + contactPage.getName());
        model.addAttribute("pageActive", "pages");

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
    public ModelAndView contact(@ModelAttribute ContactPageDto contactPage,
                                HttpServletRequest request) {

        contactService.saveContactPage(contactPage);

        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:/admin/pages");
    }

    @GetMapping("admin/pages/contactPage/deleteImage/{id}")
    public ModelAndView deleteContactImage(HttpServletRequest request, @PathVariable String id) {

        ContactCinema contactCinema = contactCinemaService.getContactCinema(Long.parseLong(id));
        contactCinema.setLogo(null);
        contactCinemaService.updateContactCinema(contactCinema);

        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

}
