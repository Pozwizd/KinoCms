package spacelab.kinocms.Dto;

import lombok.Data;

import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.Dto.Images.ImageHallDto;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.entity.Hall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link Hall}
 */
@Data
public class HallDto implements Serializable {

    Long id;
    String hallNumber;
    String description;
    MultipartFile urlSchemeImageHall;
    MultipartFile topBanner;
    List<ImageHallDto> imagesHall = new ArrayList<>();
    String seoUrl;
    String seoTitle;
    String seoKeywords;
    String seoDescription;
    Cinema cinema;

}