package spacelab.kinocms.Dto.Page;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
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
    // Долгота и широта
    @Pattern(regexp = "[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)", message = "Некорректные координаты")
    String location;
    MultipartFile logo;
}