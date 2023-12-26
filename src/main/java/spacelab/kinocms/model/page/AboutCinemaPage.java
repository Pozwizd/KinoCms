package spacelab.kinocms.model.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.Status;
import spacelab.kinocms.model.page.imagesPage.ImageAboutCinemaPage;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "about_cinema_page")
public class AboutCinemaPage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String  mainImage;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "aboutCinemaPage")
    private List<ImageAboutCinemaPage> imagesAboutCinema = new ArrayList<>();

    @Column
    private String seoUrl;

    @Column
    private String seoTitle;

    @Column
    private String seoKeywords;

    @Column
    private String seoDescription;
}