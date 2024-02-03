package spacelab.kinocms.model.Dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Value;
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.model.Film;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * DTO for {@link Film}
 */
@Data
public class FilmDto implements Serializable {
    Long id;
    String name;
    String description;
    String linkTrailer;
    @Enumerated(EnumType.STRING)
    List<TypeFilm> typeFilm;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
    Date startPremiereDate;
    Date endPremiereDate;
}