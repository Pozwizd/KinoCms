package spacelab.kinocms.Dto;

import lombok.Data;

import java.util.List;

@Data
public class MainBannersBlockDto {
    private Boolean status;
    private Integer timeChange;
    private List<MainBannersItemDto> mainBannersItemDto;

}
