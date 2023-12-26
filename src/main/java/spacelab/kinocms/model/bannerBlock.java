package spacelab.kinocms.model;

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
public class bannerBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "status")
    private Status status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bannerBlock")
    private List<Banner> banners;

    @Column(name = "timeChange")
    private Integer timeChange;

}
