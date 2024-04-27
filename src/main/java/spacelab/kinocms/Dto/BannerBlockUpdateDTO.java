package spacelab.kinocms.Dto;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BannerBlockUpdateDTO {
    public BannerBlockUpdateDTO(List<BannerUpdateDTO> banners, Integer timeChange, Boolean status) {
        this.banners = banners;
        this.timeChange = timeChange;
        this.status = status;
    }

    public BannerBlockUpdateDTO() {
    }

    // Геттеры и сеттеры
    @Valid
    private List<BannerUpdateDTO> banners;
    private Integer timeChange;
    private Boolean status;



}