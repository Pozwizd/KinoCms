package spacelab.kinocms.Dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.Dto.Images.ImageFilmDto;
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.entity.Film;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * DTO for {@link Film}
 */
@Data
public class FilmDto implements Serializable {
    Long id;
    @NotEmpty(message = "Название фильма не может быть пустым")
    @Size(min = 3, max = 100, message = "Название фильма должно содержать от 3 до 100 символов")
    String name;
    @NotEmpty(message = "Описание фильма не может быть пустым")
    @Size(min = 3, max = 1000, message = "Описание фильма должно содержать от 3 до 1000 символов")
    String description;

    MultipartFile mainImage;
    @NotEmpty(message = "Укажите ссылку на трейлер")
    String linkTrailer;
    @NotNull
    @ElementCollection(targetClass = TypeFilm.class)
    @Enumerated(EnumType.STRING)
    List<TypeFilm> typeFilm;
    @Valid
    List<ImageFilmDto> imagesFilm;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
    Date startPremiereDate;
    Date endPremiereDate;
}