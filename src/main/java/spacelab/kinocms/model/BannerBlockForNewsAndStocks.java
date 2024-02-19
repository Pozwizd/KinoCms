package spacelab.kinocms.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.enums.Status;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner_block_for_news_and_stocks")
public class BannerBlockForNewsAndStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bannerBlockForNewsAndStocks")
    private List<BannerForNewsAndStocks> banners;


    @Column
    private Integer timeChange;

}
