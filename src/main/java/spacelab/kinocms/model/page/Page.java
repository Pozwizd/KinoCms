package spacelab.kinocms.model.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.enums.Status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "page")
public class Page{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column
    private Boolean status;

    @Column
    private Date dateOfCreated;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String  mainImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
    private List<ImagePage> imagesAboutCinema = new ArrayList<>();

    @Column
    private String seoUrl;

    @Column
    private String seoTitle;

    @Column
    private String seoKeywords;

    @Column
    private String seoDescription;
}