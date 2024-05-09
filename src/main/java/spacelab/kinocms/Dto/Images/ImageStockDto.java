package spacelab.kinocms.Dto.Images;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageStockDto {


    private Long id;

    private MultipartFile url;
}
