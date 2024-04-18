package spacelab.kinocms.model.banners;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "banner_block_for_news_and_stocks")
public class BannerBlockForNewsAndStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private Boolean statusBlockBannerForNewsAndStocks;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bannerBlockForNewsAndStocks")
    private List<BannerForNewsAndStocks> banners;


    @Column
    private Integer timeChangeBlockBannerForNewsAndStocks;

}
