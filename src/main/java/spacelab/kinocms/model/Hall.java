package spacelab.kinocms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.ImagesEntity.ImageHall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hall")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String hallNumber;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    String urlSchemeImageHall;
    @Column
    private String topBanner;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hall")
    private List<ImageHall> imagesHall = new ArrayList<>();
    @Column
    private Date dateCreated;
    @Column
    private String seoUrl;
    @Column
    private String seoTitle;
    @Column
    private String seoKeywords;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String seoDescription;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

}