package spacelab.kinocms.Dto.Page;

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
    String name;
    String description;
    String mainImage;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}