package spacelab.kinocms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner_for_news_and_stocks")
public class BannerForNewsAndStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "banner_block_for_news_and_stocks_id")
    private BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks;
}