package spacelab.kinocms.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.Dto.Images.ImageStockDto;
import spacelab.kinocms.model.ImagesEntity.ImageStock;
import spacelab.kinocms.model.Stock;

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