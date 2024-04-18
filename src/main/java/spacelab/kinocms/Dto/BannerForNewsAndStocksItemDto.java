package spacelab.kinocms.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class BannerForNewsAndStocksItemDto implements Serializable {
    private Long id;
    private String url;
    private String text;
}
