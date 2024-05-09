package spacelab.kinocms.Dto.Images;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ImageFilmDto {

    private Long id;

    private MultipartFile url;

}
