package spacelab.kinocms.Dto.Page;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
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
    @NotEmpty
    @NotNull
    @Size(min = 10, max = 20)
    String phoneNumber;
    @Size(min = 10, max = 20)
    String phoneNumber2;

    String seoText;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}