package spacelab.kinocms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    @Lob
    @Column(columnDefinition = "TEXT")
    String conditions;
    @Column
    String logoPath;
    @Column
    private String topBanner;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cinema", cascade = CascadeType.REMOVE)
    private List<ImageCinema> imagesCinema = new ArrayList<>();
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cinema", cascade = CascadeType.REMOVE)
    private List<Hall> halls = new ArrayList<>();
    @Column
    private String seoUrl;
    @Column
    private String seoTitle;
    @Column
    private String seoKeywords;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String seoDescription;
}