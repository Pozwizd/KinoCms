package spacelab.kinocms.Dto;

import lombok.Data;

import org.springframework.web.multipart.MultipartFile;
import spacelab.kinocms.Dto.Images.ImageHallDto;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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