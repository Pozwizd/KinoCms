package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.Page.MainPageDto;
import spacelab.kinocms.Mapper.MainPageMapper;
import spacelab.kinocms.UploadFile;
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
public class PagesController {

    private final ImagePageService imagePageService;
    private final MainPageService mainPageService;
    private final PageService pageService;
    private final ContactService contactService;

    private final UploadFile uploadFile;

    public PagesController(ImagePageService imagePageService, MainPageService mainPageService, PageService pageService, ContactService contactService, UploadFile uploadFile) {
        this.imagePageService = imagePageService;
        this.mainPageService = mainPageService;
        this.pageService = pageService;
        this.contactService = contactService;
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
    public ModelAndView editMainPage(@ModelAttribute MainPageDto mainPageDto, HttpServletRequest request) {
        mainPageService.saveMainPage(MainPageMapper.toEntity(mainPageDto));

        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:/admin/pages");

    }

    @GetMapping("/editPage/{id}")
    public ModelAndView editBasicPage(Model model, @PathVariable long id) {
        model.addAttribute("title", "Редактирование cтраницы " + pageService.getPage(id).getName());
        model.addAttribute("pageActive", "pages");
        model.addAttribute("pageCommon", pageService.getPage(id));

        return new ModelAndView("admin/page/editPage");
    }

    @PostMapping("/editPage/{id}")
    public ModelAndView editBasicPage(@ModelAttribute Page page,
                                      @PathVariable String id,
                                      @RequestParam(name = "mainImagePage", required = false) MultipartFile mainImagePage
    ) {

        pageService.editPage(page, mainImagePage);
        return new ModelAndView("redirect:/admin/pages");
    }


    @GetMapping("/createNewPage")
    public ModelAndView createNewPage(HttpServletRequest request) {
        Page page = new Page();
        page.setDateOfCreated(Date.valueOf(LocalDate.now()));
        page.setName("Новая страница");
        pageService.savePage(page);
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

    @GetMapping("/removePage/{id}")
    public ModelAndView removePage(Model model, @PathVariable String id, HttpServletRequest request) {
        pageService.deletePage(pageService.getPage(Long.parseLong(id)));
        model.addAttribute("title", "Страницы");


        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }
//   Ajax  ====================================================================

    @GetMapping("/editPage/showMainPage/{id}")
    @ResponseBody
    public Page showMainPage(Model model, @PathVariable long id) {
        return pageService.getPage(id); // для отображения картинки в форме редактирования страницы и для отправки картинки на сервер для загрузки в форму редактирования страницы и для отправки картинки на сервер для загрузки в форму редактирования страницы и для отправки картинки на сервер для загрузки в форму редактирования страницы и для отправ�
    }

    @PostMapping("/editPage/editMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> editMainPage(@RequestPart("file") MultipartFile file,
                                               @PathVariable Long id) {


        Page page = pageService.getPage(id);
        page.setMainImage(uploadFile.uploadFile(file, page.getMainImage()));
        pageService.savePage(page);

        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editPage/deleteMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteMainPage(Model model, @PathVariable long id) {
        Page page = pageService.getPage(id);

        uploadFile.deleteFile(page.getMainImage());

        page.setMainImage(null);
        pageService.savePage(page);
        model.addAttribute("title", "Редактирование страницы " + page.getName());
        model.addAttribute("pageActive", "pages");
        model.addAttribute("pageCommon", page);
        return ResponseEntity.ok("Файл успешно удален");
    }

    @GetMapping("/editPage/showAllImages/{id}")
    @ResponseBody
    public List<ImagePage> showAllImages(@PathVariable String id) {
        return imagePageService.getAllImagesPageByPage(pageService.getPage(Long.parseLong(id)));
    }

    @GetMapping("/editPage/getImage/{id}")
    @ResponseBody
    public ImagePage getImage(@PathVariable String id) {
        return imagePageService.getImagePage(Long.parseLong(id));
    }

    @GetMapping("/editPage/deleteImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        imagePageService.deleteImagePage(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/editPage/createNewImage/{id}")
    @ResponseBody
    public ImagePage createImagePage(@PathVariable String id) {
        ImagePage imagePage = new ImagePage();
        imagePage.setPage(pageService.getPage(Long.parseLong(id)));
        imagePageService.saveImagePage(imagePage);
        return imagePageService.getLastImagePage();
    }

    @PostMapping("/editPage/editImagePage/{id}")
    @ResponseBody
    public ResponseEntity<String> editImagePage(@RequestPart("file") MultipartFile file,
                                                @PathVariable Long id) {

        ImagePage imagePage = imagePageService.getImagePage(id);
        imagePage.setUrl(uploadFile.uploadFile(file, imagePage.getUrl()));
        imagePageService.saveImagePage(imagePage);

        return ResponseEntity.ok("Файл успешно загружен");
    }
}
