package spacelab.kinocms.model.page;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * DTO for {@link ContactPage}
 */
@Data
public class ContactPageDto implements Serializable {
    Long id;
    String name;
    List<ContactCinemaDto> contactCinemas;
    String linkVideo;
    Boolean status;
    String seoUrl;
    Date dateOfCreated;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
}