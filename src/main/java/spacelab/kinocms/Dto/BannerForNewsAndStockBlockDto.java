package spacelab.kinocms.Dto;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;


@Data
public class BannerForNewsAndStockBlockDto {
    public BannerForNewsAndStockBlockDto() {
    }

    public BannerForNewsAndStockBlockDto(Boolean statusBlockBannerForNewsAndStocks, Integer timeChangeBlockBannerForNewsAndStocks, List<BannerForNewsAndStocksItemDto> banners) {
        this.statusBlockBannerForNewsAndStocks = statusBlockBannerForNewsAndStocks;
        this.timeChangeBlockBannerForNewsAndStocks = timeChangeBlockBannerForNewsAndStocks;
        this.banners = banners;
    }

    private Boolean statusBlockBannerForNewsAndStocks;
    private Integer timeChangeBlockBannerForNewsAndStocks;
    @Valid
    private List<BannerForNewsAndStocksItemDto> banners;

}
