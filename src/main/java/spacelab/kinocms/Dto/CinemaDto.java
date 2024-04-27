package spacelab.kinocms.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link Cinema}
 */
@Data
public class CinemaDto implements Serializable {
    Long id;

    @Size(min = 3, max = 50, message = "Название должно содержать от 3 до 50 символов.")
    String name;

    @Size(min = 3, max = 500, message = "Описание должно содержать от 3 до 50 символов.")
    String description;

    @Size(min = 3, max = 500, message = "Условия должны содержать от 3 до 50 символов.")
    String conditions;
    List<Hall> Halls = new ArrayList<>();
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}