package spacelab.kinocms.Dto.Page;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.entity.page.Page;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * DTO for {@link Page}
 */
@Data
public class PageDto implements Serializable {

    private Long id;
    private Boolean status;
    private Date dateOfCreated;
    private String name;
    private String description;
    private MultipartFile mainImage;
    private List<ImagePageDto> imagesAboutCinema;
    private String seoUrl;
    private String seoTitle;
    private String seoKeywords;
    private String seoDescription;
}