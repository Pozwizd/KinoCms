package spacelab.kinocms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.ImagesEntity.ImageStock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private Boolean status;
    @Column
    private String name;
    @Column
    private Date datePosting;
    @Column
    private Date dateCreated;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String mainImage;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stock")
    private List<ImageStock> imagesStock = new ArrayList<>();
    @Column
    private String linkVideo;
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
