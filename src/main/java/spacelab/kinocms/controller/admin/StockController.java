package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.service.ImageStockService;
import spacelab.kinocms.service.StockService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin/stocks")
public class StockController {

    private static final String UPLOAD_FOLDER = Paths.get("images").toFile().getAbsolutePath() + "/";

    private final StockService stockService;

    private final ImageStockService imageStockService;

    public StockController(StockService stockService, ImageStockService imageStockService) {
        this.stockService = stockService;
        this.imageStockService = imageStockService;
    }

    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("title", "Акции");
        model.addAttribute("pageActive", "Stocks");

        model.addAttribute("stocks", stockService.listAllStocks());

        return new ModelAndView("admin/stock/stocks");
    }

    @GetMapping("/editStock/{id}")
    public ModelAndView showEditMainPage(Model model, @PathVariable String id) {
        Stock stock = stockService.getStock(Long.parseLong(id));
        model.addAttribute("title", "Страница акции: " + stock.getName());
        model.addAttribute("pageActive", "Stocks");

        model.addAttribute("stock", stock);
        return new ModelAndView("admin/stock/editStock");
    }

    @PostMapping("/editStock/{id}")
    public ModelAndView editBasicPage(@ModelAttribute Stock stock,
                                      @PathVariable String id,
                                      @RequestParam(name = "mainImagePage", required = false)
                                          MultipartFile mainImagePage) {

        stockService.updateStock(stock);
        return new ModelAndView("redirect:/admin/stocks");
    }


    @GetMapping("/createStock")
    public ModelAndView createNewPage(Model model,HttpServletRequest request) {
        Stock stock = new Stock();
        stock.setDateCreated(Date.valueOf(LocalDate.now()));
        stock.setName("Новая акция");
        stockService.saveStock(stock);
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

    @GetMapping("/removeStock/{id}")
    public ModelAndView removePage(Model model, @PathVariable String id, HttpServletRequest request) {
        stockService.deleteStock(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }

//   Ajax  ====================================================================

    @GetMapping("/editStock/showMainPage/{id}")
    @ResponseBody
    public Stock showMainImageStock(Model model, @PathVariable long id) {
        return stockService.getStock(id);
    }

    @PostMapping("/editStock/editMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> editMainImageStock(@RequestPart("file") MultipartFile file,
                                                     @PathVariable Long id) {

        Stock stock = stockService.getStock(id);
        if (stock.getMainImage() != null) {
            String filePath = Paths.get("").toFile().getAbsolutePath() + stock.getMainImage();
            File oldFile = new File(filePath);
            oldFile.delete();
        }
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(UPLOAD_FOLDER + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stock.setMainImage("/images/" + fileName);
        stockService.saveStock(stock);

        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editStock/deleteMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImageStock(Model model, @PathVariable long id) {
        Stock stock = stockService.getStock(id);
        if (stock.getMainImage() != null) {
            String filePath = Paths.get("").toFile().getAbsolutePath() + stock.getMainImage();
            File oldFile = new File(filePath);
            oldFile.delete();
        }
        stock.setMainImage(null);
        stockService.saveStock(stock);
        model.addAttribute("title", "Редактирование страницы " + stock.getName());
        model.addAttribute("stockActive", "pages");
        model.addAttribute("stockCommon", stock);
        return ResponseEntity.ok("Файл успешно удален");
    }

    @GetMapping("/editStock/showAllImages/{id}")
    @ResponseBody
    public List<ImageStock> showAllImages(@PathVariable String id) {
        return imageStockService.getAllImagesStockByStock(stockService.getStock(Long.parseLong(id)));
    }

    @GetMapping("/editStock/getImage/{id}")
    @ResponseBody
    public ImageStock getImage(@PathVariable String id) {
        return imageStockService.getImageStock(Long.parseLong(id));
    }

    @GetMapping("/editStock/deleteImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        imageStockService.deleteImageStock(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/editStock/createNewImage/{id}")
    @ResponseBody
    public ImageStock createImageStock(@PathVariable String id) {
        ImageStock imageStock = new ImageStock();
        imageStock.setStock(stockService.getStock(Long.parseLong(id)));
        imageStockService.saveImageStock(imageStock);
        return imageStockService.getLastImageStock();
    }

    @PostMapping("/editStock/editImageStock/{id}")
    @ResponseBody
    public ResponseEntity<String> editImageStock(@RequestPart("file") MultipartFile file,
                                                 @PathVariable Long id) {

        ImageStock imageStock = imageStockService.getImageStock(id);
        if (imageStock.getUrl() != null) {
            String filePath = Paths.get("").toFile().getAbsolutePath() + imageStock.getUrl();
            File oldFile = new File(filePath);
            oldFile.delete();
        }
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(UPLOAD_FOLDER + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageStock.setUrl("/images/" + fileName);
        imageStockService.saveImageStock(imageStock);

        return ResponseEntity.ok("Файл успешно загружен");
    }
}
