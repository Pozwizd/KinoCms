package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.FilmDto;
import spacelab.kinocms.Dto.Page.MainPageDto;
import spacelab.kinocms.Dto.Page.PageDto;
import spacelab.kinocms.Mapper.MainPageMapper;
import spacelab.kinocms.Mapper.PageMapper;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.page.ImagePage;
import spacelab.kinocms.model.page.Page;
import spacelab.kinocms.service.ContactService;
import spacelab.kinocms.service.ImagePageService;
import spacelab.kinocms.service.MainPageService;
import spacelab.kinocms.service.PageService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("admin/pages")
public class  PagesController {

    private final ImagePageService imagePageService;
    private final MainPageService mainPageService;
    private final PageService pageService;
    private final ContactService contactService;
    private final PageMapper pageMapper;
    private final UploadFile uploadFile;

    public PagesController(ImagePageService imagePageService, MainPageService mainPageService, PageService pageService, ContactService contactService, PageMapper pageMapper, UploadFile uploadFile) {
        this.imagePageService = imagePageService;
        this.mainPageService = mainPageService;
        this.pageService = pageService;
        this.contactService = contactService;
        this.pageMapper = pageMapper;
        this.uploadFile = uploadFile;
    }

    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Страницы");
        model.addAttribute("pageActive", "pages");

        model.addAttribute("mainPage", mainPageService.getMainPage());
        model.addAttribute("basicPages", pageService.getBasicPages());
        model.addAttribute("contactPage", contactService.getContactPage());
        model.addAttribute("newsPages", pageService.getNewPages());

        return new ModelAndView("admin/page/pages");
    }

    @GetMapping("/editMainPage")
    public ModelAndView showEditMainPage(Model model) {
        MainPageDto mainPageDto = MainPageMapper.toDto(mainPageService.getMainPage());
        model.addAttribute("title",  "Редактирование cтраницы " + mainPageDto.getName());
        model.addAttribute("pageActive", "pages");
        model.addAttribute("mainPage", mainPageDto);
        return new ModelAndView("admin/page/editMainPage");
    }

    @PostMapping("/editMainPage/{id}")
    public ModelAndView editMainPage(@Valid @ModelAttribute("mainPage") MainPageDto mainPageDto,
                                     BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Редактирование cтраницы " + mainPageService.getMainPage().getName());
            model.addAttribute("pageActive", "pages");
            return new ModelAndView("admin/page/editMainPage");
        }

        mainPageService.saveMainPage(MainPageMapper.toEntity(mainPageDto));

        return new ModelAndView("redirect:/admin/pages");

    }

    @GetMapping("/editPage/{id}")
    public ModelAndView editBasicPage(Model model, @PathVariable long id) {
        model.addAttribute("title", "Редактирование cтраницы " + pageService.getPage(id).getName());
        model.addAttribute("pageActive", "pages");
        model.addAttribute("pageCommon", pageMapper.toDto(pageService.getPage(id)));

        return new ModelAndView("admin/page/editPage");
    }

    @PostMapping("/editPage/{id}")
    public ModelAndView editBasicPage(@Valid @ModelAttribute("pageCommon") PageDto pageDto,
                                      BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Редактирование страницы " + pageDto.getName());
            model.addAttribute("pageActive", "pages");
            model.addAttribute("pageCommon", pageService.getPage(pageDto.getId()));
            return new ModelAndView("admin/page/editPage");
        }
        if(pageDto.getImagesAboutCinema() != null) {
            pageDto.getImagesAboutCinema().removeIf(imageFilm -> imageFilm.getId() == null
                    && imageFilm.getUrl().getSize() == 0);
            if(pageDto
                    .getImagesAboutCinema()
                    .stream()
                    .anyMatch(image -> !uploadFile
                            .isAllowedImageTypeAndSize(image.getUrl()))){
                model.addAttribute("title", "Редактирование страницы " + pageDto.getName());
                model.addAttribute("pageActive", "pages");
                model.addAttribute("pageCommon", pageService.getPage(pageDto.getId()));
                return new ModelAndView("admin/page/editPage");
            }

        }
        pageService.updatePage(pageMapper.toEntity(pageDto));
        return new ModelAndView("redirect:/admin/pages");
    }


    @GetMapping("/createNewPage")
    public ModelAndView createNewPage(HttpServletRequest request) {
        Page page = new Page();
        page.setName("Новая страница");
        page.setId(pageService.idLastPage() + 1);
        page.setMainImage(null);

        return new ModelAndView("admin/page/editPage", "pageCommon", page);
    }

    @GetMapping("/removePage/{id}")
    public ModelAndView removePage(Model model, @PathVariable String id, HttpServletRequest request) {
        pageService.deletePage(pageService.getPage(Long.parseLong(id)));
        model.addAttribute("title", "Страницы");


        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }
}
