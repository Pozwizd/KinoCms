package spacelab.kinocms.Dto;

import lombok.Data;

import java.util.List;


@Data
public class BannerForNewsAndStockBlockDto {
    private Boolean statusBlockBannerForNewsAndStocks;
    private Integer timeChangeBlockBannerForNewsAndStocks;
    private List<BannerForNewsAndStocksItemDto> mainBannersItemDto;

}
