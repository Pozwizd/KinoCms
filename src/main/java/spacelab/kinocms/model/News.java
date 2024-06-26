package spacelab.kinocms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.ImagesEntity.ImageNews;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "news")
    private List<ImageNews> imagesNews = new ArrayList<>();
    @Column
    private String linkVideo;
    @Column
    private String seoUrl;
    @Column
    private String seoTitle;
    @Column
    private String seoKeywords;
    @Column
    private String seoDescription;
}
