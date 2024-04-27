package spacelab.kinocms.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Hall}
 */
@Data
public class HallDto implements Serializable {
    Long id;
    @NotEmpty(message = "Название фильма не может быть пустым")
    @Size(min = 3, max = 100, message = "Название фильма должно содержать от 3 до 100 символов")
    String hallNumber;
    @NotEmpty(message = "Описание не может быть пустым")
    String description;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
    Cinema cinema;
}