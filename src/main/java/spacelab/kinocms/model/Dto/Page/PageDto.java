package spacelab.kinocms.model.Dto.Page;

import lombok.Data;
import lombok.Value;
import spacelab.kinocms.model.page.Page;

import java.io.Serializable;
import java.sql.Date;

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