package spacelab.kinocms.model.Dto.Page;

import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.model.page.ContactCinema;

import java.io.Serializable;

/**
 * DTO for {@link ContactCinema}
 */
@Data
public class ContactCinemaDto implements Serializable {
    Long id;
    String name;
    Boolean status;
    String address;
    String location;
    MultipartFile logo;
}