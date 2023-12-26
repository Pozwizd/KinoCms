package spacelab.kinocms.model.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.Status;
import spacelab.kinocms.model.page.imagesPage.ImageCafeBarPage;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cafe_bar_page")
public class CafeBarPage{

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

    @OneToMany(mappedBy = "cafeBarPage")
    private List<ImageCafeBarPage> imagesCafeBarPage = new ArrayList<>();

    @Column
    private String seoUrl;

    @Column
    private String seoTitle;

    @Column
    private String seoKeywords;

    @Column
    private String seoDescription;
}