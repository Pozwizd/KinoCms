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
@AllArgsConstructor
public class StockController {

    private final StockService stockService;
    private final UploadFile uploadFile;
    private final ImageStockService imageStockService;


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

    @GetMapping("/editStock/{nothing}/showMainPage/{id}")
    @ResponseBody
    public Stock showMainImageStock(Model model, @PathVariable long id, @PathVariable String nothing) {
        return stockService.getStock(id);
    }

    @PostMapping("/editStock/{nothing}/editMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> editMainImageStock(@RequestPart("file") MultipartFile file,
                                                     @PathVariable Long id, @PathVariable String nothing) {

        Stock stock = stockService.getStock(id);

        stock.setMainImage(uploadFile.uploadFile(file, stock.getMainImage()));
        stockService.saveStock(stock);

        return ResponseEntity.ok("Файл успешно загружен");
    }

    @PostMapping("/editStock/{nothing}/deleteMainPage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImageStock(Model model, @PathVariable long id, @PathVariable String nothing) {
        Stock stock = stockService.getStock(id);
        uploadFile.deleteFile(stock.getMainImage());
        stock.setMainImage(null);
        stockService.saveStock(stock);
        return ResponseEntity.ok("Файл успешно удален");
    }

    @GetMapping("/editStock/{nothing}/showAllImages/{id}")
    @ResponseBody
    public List<ImageStock> showAllImages(@PathVariable String id, @PathVariable String nothing) {
        return imageStockService.getAllImagesStockByStock(stockService.getStock(Long.parseLong(id)));
    }

    @GetMapping("/editStock/{nothing}/getImage/{id}")
    @ResponseBody
    public ImageStock getImage(@PathVariable String id, @PathVariable String nothing) {
        return imageStockService.getImageStock(Long.parseLong(id));
    }

    @GetMapping("/editStock/{nothing}/deleteImage/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteImage(@PathVariable String id, @PathVariable String nothing) {
        imageStockService.deleteImageStock(Long.parseLong(id));
        return ResponseEntity.ok("Image deleted successfully");
    }

    @GetMapping("/editStock/{nothing}/createNewImage/{id}")
    @ResponseBody
    public ImageStock createImageStock(@PathVariable String id, @PathVariable String nothing) {
        ImageStock imageStock = new ImageStock();
        imageStock.setStock(stockService.getStock(Long.parseLong(id)));
        imageStockService.saveImageStock(imageStock);
        return imageStockService.getLastImageStock();
    }

    @PostMapping("/editStock/{nothing}/editImageStock/{id}")
    @ResponseBody
    public ResponseEntity<String> editImageStock(@RequestPart("file") MultipartFile file,
                                                 @PathVariable Long id, @PathVariable String nothing) {

        ImageStock imageStock = imageStockService.getImageStock(id);

        imageStock.setUrl(uploadFile.uploadFile(file, imageStock.getUrl()));
        imageStockService.saveImageStock(imageStock);

        return ResponseEntity.ok("Файл успешно загружен");
    }
}
