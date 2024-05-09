package spacelab.kinocms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.enums.TypeFilm;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Film {
    public Film(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    private String mainImage;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ImageFilm> imagesFilm = new ArrayList<>();

    @OneToMany(mappedBy = "filmId", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Session> sessions = new ArrayList<>();

    @Lob
    @Column(columnDefinition = "TEXT")
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

    @Lob
    @Column(columnDefinition = "TEXT")
    private String seoDescription;

    @Column
    private Date startPremiereDate;

    @Column
    private Date endPremiereDate;
}
