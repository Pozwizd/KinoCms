package spacelab.kinocms.entity.banners;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "banner_for_news_and_stocks")
public class BannerForNewsAndStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String pathImage;

    private String url = "";

    private String title = "";

    @ManyToOne
    @JoinColumn(name = "banner_block_for_news_and_stocks_id")
    @JsonIgnore
    private BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks;
}