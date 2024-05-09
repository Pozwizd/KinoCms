package spacelab.kinocms.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.Dto.Images.ImageNewsDto;
import spacelab.kinocms.model.News;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


/**
 * DTO for {@link News}
 */
@Data
public class NewsDto implements Serializable {
    Long id;
    Boolean status;
    @Size(min = 3, max = 50, message = "Название должно содержать от 3 до 50 символов.")
    String name;
    @NotNull(message = "Пожалуйста, введите дату публикации")
    LocalDate datePosting;
    @NotEmpty(message = "Пожалуйста, введите дату описание")
    String description;
    MultipartFile mainImage;
    @Valid
    List<ImageNewsDto> imagesNews;
    @NotEmpty(message = "Пожалуйста, введите ссылку на видео")
    String linkVideo;
    @NotEmpty
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}