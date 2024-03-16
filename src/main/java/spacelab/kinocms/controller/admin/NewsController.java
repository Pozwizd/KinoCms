package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
public class NewsController {

    private static final String UPLOAD_FOLDER = Paths.get("images").toFile().getAbsolutePath() + "/";

    private final NewsService newsService;
    private final ImageNewsService imageNewsService;

    public NewsController(NewsService newsService, ImageNewsService imageNewsService) {
        this.newsService = newsService;
        this.imageNewsService = imageNewsService;
    }

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

    @GetMapping("/editNews/showMainPage/{id}")
    @ResponseBody
    public String showMainImageNews(Model model, @PathVariable long id) {
        News news = newsService.getNews(id);
        return news.getMainImage();
    }

    @PostMapping("/editNews/editMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> editMainImageNews(@RequestPart("file") MultipartFile file,
                                                    @PathVariable Long id) {

        News news = newsService.getNews(id);
        if (news.getMainImage() != null) {
            String filePath = Paths.get("").toFile().getAbsolutePath() + news.getMainImage();
            File oldFile = new File(filePath);
            oldFile.delete();
        }
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(UPLOAD_FOLDER + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        news.setMainImage("/images/" + fileName);
        newsService.saveNews(news);

        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editNews/deleteMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImageNews(Model model, @PathVariable long id) {
        News news = newsService.getNews(id);
        if (news.getMainImage() != null) {
            String filePath = Paths.get("").toFile().getAbsolutePath() + news.getMainImage();
            File oldFile = new File(filePath);
            oldFile.delete();
        }
        news.setMainImage(null);
        newsService.saveNews(news);
        model.addAttribute("title", "Редактирование новости " + news.getName());
        model.addAttribute("newsActive", "news");
        model.addAttribute("newsCommon", news);
        return ResponseEntity.ok("Файл успешно удален");
    }

    @GetMapping("/editNews/showAllImages/{id}")
    @ResponseBody
    public List<ImageNews> showAllImages(@PathVariable String id) {
        List<ImageNews> images = imageNewsService.getAllImagesNewsByNews(newsService.getNews(Long.parseLong(id)));
        System.out.println(images);
        return images;
    }

    @GetMapping("/editNews/getImage/{id}")
    @ResponseBody
    public ImageNews getImage(@PathVariable String id) {
        return imageNewsService.getImageNews(Long.parseLong(id));
    }

    @GetMapping("/editNews/deleteImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        imageNewsService.deleteImageNews(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/editNews/createNewImage/{id}")
    @ResponseBody
    public ImageNews createImageNews(@PathVariable String id) {
        ImageNews imageNews = new ImageNews();
        imageNews.setNews(newsService.getNews(Long.parseLong(id)));
        imageNewsService.saveImageNews(imageNews);
        return imageNewsService.getLastImageNews();
    }

    @PostMapping("/editNews/editImageNews/{id}")
    @ResponseBody
    public ResponseEntity<String> editImageNews(@RequestPart("file") MultipartFile file,
                                                @PathVariable Long id) {

        ImageNews imageNews = imageNewsService.getImageNews(id);
        if (imageNews.getUrl() != null) {
            String filePath = Paths.get("").toFile().getAbsolutePath() + imageNews.getUrl();
            File oldFile = new File(filePath);
            oldFile.delete();
        }
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(UPLOAD_FOLDER + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageNews.setUrl("/images/" + fileName);
        imageNewsService.saveImageNews(imageNews);

        return ResponseEntity.ok("Файл успешно загружен");
    }
}

