package spacelab.kinocms.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import spacelab.kinocms.model.News;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


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
    @NotEmpty(message = "Пожалуйста, введите ссылку на видео")
    String linkVideo;
    @NotEmpty
    String seoUrl;
    @NotEmpty
    String seoTitle;
    @NotEmpty
    String seoKeywords;
    @NotEmpty
    String seoDescription;
}