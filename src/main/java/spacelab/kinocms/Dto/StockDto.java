package spacelab.kinocms.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.Dto.Images.ImageStockDto;
import spacelab.kinocms.entity.Stock;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link Stock}
 */
@Data
public class StockDto implements Serializable {

    Long id;
    Boolean status;
    String name;
    Date datePosting;
    Date dateCreated;
    String description;
    MultipartFile mainImage;
    List<ImageStockDto> imagesStock = new ArrayList<>();
    String linkVideo;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}