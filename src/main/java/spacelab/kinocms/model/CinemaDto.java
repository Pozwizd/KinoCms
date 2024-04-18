package spacelab.kinocms.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link Cinema}
 */
@Data
public class CinemaDto implements Serializable {
    Long id;
    @NotEmpty
    @Size(min = 3, max = 50, message = "Название должно содержать от 3 до 50 символов.")
    String name;
    String description;
    String conditions;
    List<Hall> Halls = new ArrayList<>();
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}