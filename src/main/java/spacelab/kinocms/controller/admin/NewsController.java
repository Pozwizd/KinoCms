package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import spacelab.kinocms.model.News;
import spacelab.kinocms.service.ImageNewsService;
import spacelab.kinocms.service.NewsService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin/news")
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final UploadFile uploadFile;
    private final ImageNewsService imageNewsService;



    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Новости");
        model.addAttribute("pageActive", "news");

        model.addAttribute("newsList", newsService.listAllNews());

        return new ModelAndView("admin/news/news");
    }

    @GetMapping("/editNews/{id}")
    public ModelAndView showEditMainPage(Model model, @PathVariable String id) {
        News news = newsService.getNews(Long.parseLong(id));
        model.addAttribute("title", "Редактирование новости " + news.getName());
        model.addAttribute("pageActive", "News");

        model.addAttribute("news", news);
        return new ModelAndView("admin/news/editNews");
    }

    @PostMapping("/editNews/{id}")
    public ModelAndView editBasicPage(@ModelAttribute News news,
                                      @PathVariable String id,
                                      @RequestParam(name = "mainImagePage", required = false)
                                      MultipartFile mainImagePage) {

        newsService.updateNews(news);
        return new ModelAndView("redirect:/admin/news");
    }

    @GetMapping("/createNews")
    public ModelAndView createNewPage(Model model, HttpServletRequest request) {
        News news = new News();
        news.setDateCreated(Date.valueOf(LocalDate.now()));
        news.setName("Новая новость");
        newsService.saveNews(news);
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

    @GetMapping("/removeNews/{id}")
    public ModelAndView removePage(Model model, @PathVariable String id, HttpServletRequest request) {
        newsService.deleteNews(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

// Ajax ====================================================================

    @GetMapping("/editNews/{id}/showMainPage/")
    @ResponseBody
    public News showMainImageNews(Model model, @PathVariable long id) {
        return newsService.getNews(id);
    }

    @PostMapping("/editNews/{id}/editMainPage/")
    @ResponseBody
    public ResponseEntity<String> editMainImageNews(@RequestPart("file") MultipartFile file,
                                                    @PathVariable Long id) {

        News news = newsService.getNews(id);
        news.setMainImage(uploadFile.uploadFile(file, news.getMainImage()));
        newsService.saveNews(news);

        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editNews/{id}/deleteMainPage/")
    @ResponseBody
    public ResponseEntity<String> deleteImageNews(Model model, @PathVariable long id) {
        News news = newsService.getNews(id);
        uploadFile.deleteFile(news.getMainImage());
        news.setMainImage(null);
        newsService.saveNews(news);
        return ResponseEntity.ok("Файл успешно удален");
    }

    @GetMapping("/editNews/{id}/showAllImages/")
    @ResponseBody
    public List<ImageNews> showAllImages(@PathVariable String id) {
        return imageNewsService.getAllImagesNewsByNews(newsService.getNews(Long.parseLong(id)));
    }

    @GetMapping("/editNews/{nothing}/getImage/{id}")
    @ResponseBody
    public ImageNews getImage(@PathVariable String id, @PathVariable String nothing) {
        return imageNewsService.getImageNews(Long.parseLong(id));
    }

    @GetMapping("/editNews/{nothing}/deleteImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImage(@PathVariable String id, @PathVariable String nothing) {
        uploadFile.deleteFile(imageNewsService.getImageNews(Long.parseLong(id)).getUrl());
        imageNewsService.deleteImageNews(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/editNews/{id}/createNewImage/")
    @ResponseBody
    public ImageNews createImageNews(@PathVariable String id, @PathVariable String nothing) {
        ImageNews imageNews = new ImageNews();
        imageNews.setNews(newsService.getNews(Long.parseLong(id)));
        imageNewsService.saveImageNews(imageNews);
        imageNews = imageNewsService.getLastImageNews(id);
        return imageNews;
    }

    @PostMapping("/editNews/{nothing}/editImageNews/{id}")
    @ResponseBody
    public ResponseEntity<String> editImageNews(@RequestPart("file") MultipartFile file,
                                                @PathVariable Long id) {


        ImageNews imageNews = imageNewsService.getImageNews(id);
        imageNews.setUrl(uploadFile.uploadFile(file, imageNews.getUrl()));
        imageNewsService.saveImageNews(imageNews);

        return ResponseEntity.ok("Файл успешно загружен");
    }
}

