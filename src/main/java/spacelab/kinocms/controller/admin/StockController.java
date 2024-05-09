package spacelab.kinocms.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spacelab.kinocms.Dto.NewsDto;
import spacelab.kinocms.Dto.StockDto;
import spacelab.kinocms.Mapper.StockMapper;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.News;
import spacelab.kinocms.model.Stock;
import spacelab.kinocms.service.ImageStockService;
import spacelab.kinocms.service.StockService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("admin/stocks")
@AllArgsConstructor
public class StockController {

    private final StockService stockService;
    private final UploadFile uploadFile;
    private final StockMapper stockMapper;


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
    public ModelAndView editBasicPage(@Valid @ModelAttribute("stock") StockDto stockDto,
                                      BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Редактирование акции " + stockDto.getName());
            model.addAttribute("pageActive", "stock");
            model.addAttribute("stock", stockService.getStock(stockDto.getId()));
            return new ModelAndView("admin/stock/editStock");
        }
        if(stockDto.getImagesStock() != null) {
            stockDto.getImagesStock().removeIf(imageStock -> imageStock.getId() == null
                    && imageStock.getUrl().getSize() == 0);
            if(stockDto
                    .getImagesStock()
                    .stream()
                    .anyMatch(image -> !uploadFile
                            .isAllowedImageTypeAndSize(image.getUrl()))){
                model.addAttribute("title", "Редактирование акции " + stockDto.getName());
                model.addAttribute("pageActive", "stock");
                model.addAttribute("stock", stockService.getStock(stockDto.getId()));
                return new ModelAndView("admin/stock/editStock");
            }

        }
        stockService.updateStock(stockMapper.toEntity(stockDto));

        return new ModelAndView("redirect:/admin/stocks");
    }


    @GetMapping("/createStock")
    public ModelAndView createNewPage(Model model, HttpServletRequest request) {


        Stock stock = new Stock();
        stock.setId(stockService.idLastStock() + 1);
        stock.setName("Новый новость");
        stock.setMainImage(null);

        return new ModelAndView("admin/stock/editStock", "stock", stock);
    }

    @GetMapping("/removeStock/{id}")
    public ModelAndView removePage(Model model, @PathVariable String id, HttpServletRequest request) {
        stockService.deleteStock(Long.parseLong(id));
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }
}
