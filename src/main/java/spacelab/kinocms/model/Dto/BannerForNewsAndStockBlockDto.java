package spacelab.kinocms.model.Dto;

import lombok.Data;

import java.util.List;


@Data
public class BannerForNewsAndStockBlockDto {
    private Boolean status;
    private Integer timeChange;
    private List<BannerForNewsAndStocksItemDto> mainBannersItemDto;

}
