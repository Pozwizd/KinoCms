package spacelab.kinocms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column(name = "conditions")
    String conditions;
    @Column(name = "logoPath")
    String logoPath;
    @Column(name = "topBanner")
    private String topBanner;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cinema")
    private List<ImageCinema> imagesCinema = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cinema")
    private List<Hall> Halls = new ArrayList<>();
    @Column
    private String seoUrl;
    @Column
    private String seoTitle;
    @Column
    private String seoKeywords;
    @Column
    private String seoDescription;
}