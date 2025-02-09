package spacelab.kinocms.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BannerForNewsAndStocksItemDto{

    public BannerForNewsAndStocksItemDto(Long id, String url, String title, MultipartFile pathImage) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.pathImage = pathImage;
    }

    public BannerForNewsAndStocksItemDto() {
    }

    private Long id;
    private String url;
    private String title;
    private MultipartFile pathImage;
}
