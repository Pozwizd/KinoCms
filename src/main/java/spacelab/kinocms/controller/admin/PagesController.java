package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.page.MainPage;
import spacelab.kinocms.model.page.Page;
import spacelab.kinocms.service.ContactService;
import spacelab.kinocms.service.MainPageService;
import spacelab.kinocms.service.PageService;

import java.util.List;

@Controller
@RequestMapping("admin/pages")
public class PagesController {

    private final MainPageService mainPageService;
    private final PageService pageService;
    private final ContactService contactService;

    public PagesController(MainPageService mainPageService, PageService pageService, ContactService contactService) {
        this.mainPageService = mainPageService;
        this.pageService = pageService;
        this.contactService = contactService;
    }

    @GetMapping({"/", ""})
    public ModelAndView index(Model model){
        model.addAttribute("title", "Страницы");
        model.addAttribute("pageActive", "pages");

        model.addAttribute("mainPage", mainPageService.getMainPage());
        model.addAttribute("basicPages", pageService.getBasicPages());
        model.addAttribute("contactPage", contactService.getContactPage());
        model.addAttribute("newsPages", pageService.getNewPages());

        return new ModelAndView("admin/page/pages");
    }

    @GetMapping("/editMainPage")
    public ModelAndView showEditMainPage(Model model){
        MainPage  mainPage = mainPageService.getMainPage();
        model.addAttribute("title", mainPage.getName());
        model.addAttribute("pageActive", "pages");
        model.addAttribute("mainPage", mainPage);
        return new ModelAndView("admin/page/editMainPage");
    }

    @PostMapping("/editMainPage/{id}")
    public ModelAndView editMainPage(@ModelAttribute MainPage mainPage, HttpServletRequest request){
        mainPageService.updateMainPage(mainPage);

        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);

    }



    @GetMapping("/editPage/{id}")
    public ModelAndView editBasicPage(Model model, @PathVariable long id){
        model.addAttribute("title","Редактирование cтраницы "  + pageService.getPage(id).getName());
        model.addAttribute("pageActive", "pages");
        model.addAttribute("page", pageService.getPage(id));
        return new ModelAndView("admin/page/editPage");
    }

    @PostMapping("/editPage/{id}")
    public ModelAndView editBasicPage(@ModelAttribute Page page,
                                      @PathVariable String id,
                                      @RequestParam(name = "mainImagePage", required = false) MultipartFile  mainImagePage,
                                      @RequestParam(name = "imageAboutCinema[]", required = false) List<MultipartFile> imagesAboutCinema
                                      ){

        pageService.editPage(page, mainImagePage, imagesAboutCinema);

        return new ModelAndView("redirect:/admin/pages");
    }



    @GetMapping("/editContactPage")
    public ModelAndView editContactPage(Model model){
        return new ModelAndView("admin/page/editContactPage");
    }

    @GetMapping("/add")
    public ModelAndView add(Model model){
        return new ModelAndView("redirect:/");
    }
}
