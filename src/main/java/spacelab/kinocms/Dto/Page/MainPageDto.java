package spacelab.kinocms.Dto.Page;

import lombok.Data;
import lombok.Value;
import spacelab.kinocms.model.page.MainPage;

import java.io.Serializable;

/**
 * DTO for {@link MainPage}
 */
@Data
public class MainPageDto implements Serializable {
    Long id;
    String name;
    Boolean status;
    String phoneNumber;
    String phoneNumber2;
    String seoText;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}