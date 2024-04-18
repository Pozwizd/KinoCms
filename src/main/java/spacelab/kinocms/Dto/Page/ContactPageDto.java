package spacelab.kinocms.Dto.Page;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import spacelab.kinocms.model.page.ContactPage;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link ContactPage}
 */
@Data
public class ContactPageDto implements Serializable {
    Long id;
    @NotNull(message = "Name is required")
    String address;
    String name;
    List<ContactCinemaDto> contactCinemas;
    String linkVideo;
    Boolean status;
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}