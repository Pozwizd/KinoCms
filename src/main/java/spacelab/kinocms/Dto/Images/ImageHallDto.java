package spacelab.kinocms.Dto.Images;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageHallDto {
    private Long id;

    private MultipartFile url;

}
