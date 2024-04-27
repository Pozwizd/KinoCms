package spacelab.kinocms.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
public class BannerUpdateDTO {
    public BannerUpdateDTO(String id, String url, String title, MultipartFile pathImage) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.pathImage = pathImage;
    }

    public BannerUpdateDTO() {
    }

    private String id;
    private String url;
    private String title;
    private MultipartFile pathImage;
}
