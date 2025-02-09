package spacelab.kinocms.entity.banners;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String pathImage;

    private String url = "";

    private String title = "";

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "banner_block_id")
    private BannerBlock bannerBlock;

}