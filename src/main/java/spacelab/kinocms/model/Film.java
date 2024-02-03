package spacelab.kinocms.model;

import jakarta.persistence.*;
import lombok.*;
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "main_image")
    String mainImage;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private List<ImageFilm> imagesFilm = new ArrayList<>();

    @Column
    private String linkTrailer;

    @ElementCollection(targetClass = TypeFilm.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "film_type", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "type")
    private List<TypeFilm> typeFilm = new ArrayList<>();


    @Column
    private String seoUrl;
    @Column
    private String seoTitle;
    @Column
    private String seoKeywords;
    @Column
    private String seoDescription;
    @Column
    private Date startPremiereDate;
    @Column
    private Date endPremiereDate;
}
