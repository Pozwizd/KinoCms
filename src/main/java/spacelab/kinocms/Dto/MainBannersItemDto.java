package spacelab.kinocms.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MainBannersItemDto implements Serializable {
    private Long id;
    private String url;
    private String text;
}
