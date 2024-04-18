package spacelab.kinocms.Dto.Page;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import spacelab.kinocms.model.page.Page;

import java.io.Serializable;

/**
 * DTO for {@link Page}
 */
@Data
public class PageDto implements Serializable {
    Long id;
    Boolean status;
    @NotEmpty
    @Size(min = 3, max = 50, message = "Название должно содержать от 3 до 50 символов.")
    String name;
    @NotEmpty
    @Size(min = 3, max = 500, message = "Описание должно содержать от 3 до 50 символов.")
    String description;
    @NotEmpty
    @Size(min = 3, max = 100, message = "URL должно содержать от 3 до 50 символов.")
    String seoUrl;
    @NotEmpty
    @Size(min = 3, max = 100, message = "SeoTitle должно содержать от 3 до 50 символов.")
    String seoTitle;
    @NotEmpty
    @Size(min = 3, max = 100, message = "SeoKeywords должно содержать от 3 до 50 символов.")
    String seoKeywords;
    @NotEmpty
    @Size(min = 3, max = 1000, message = "SeoDescription должно содержать от 3 до 1000 символов.")
    String seoDescription;
}