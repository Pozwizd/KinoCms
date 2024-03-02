package spacelab.kinocms.model.banners;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner_block")
public class BannerBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "status")
    private Boolean status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bannerBlock")
    private List<Banner> banners;

    @Column
    private String timeChange;

}
