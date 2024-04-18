package spacelab.kinocms.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import spacelab.kinocms.model.Stock;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link Stock}
 */
@Data
public class StockDto implements Serializable {
    Long id;
    Boolean status;
    @NotEmpty
    @Size(min = 3, max = 50, message = "Название должно содержать от 3 до 50 символов.")
    String name;
    @NotNull(message = "Пожалуйста, введите дату публикации")
    Date datePosting;
    Date dateCreated;
    String description;
    String mainImage;
    String linkVideo;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}