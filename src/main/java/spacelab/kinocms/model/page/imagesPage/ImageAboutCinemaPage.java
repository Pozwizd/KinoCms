package spacelab.kinocms.model.page.imagesPage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.page.AboutCinemaPage;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_about_cinema_page")
public class ImageAboutCinemaPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "about_cinema_page_id")
    private AboutCinemaPage aboutCinemaPage;

}