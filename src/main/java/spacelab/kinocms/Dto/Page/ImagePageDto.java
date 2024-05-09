package spacelab.kinocms.Dto.Page;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ImagePageDto {


    private Long id;

    private MultipartFile url;
}
