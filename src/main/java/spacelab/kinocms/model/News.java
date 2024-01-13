package spacelab.kinocms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import spacelab.kinocms.enums.Status;
import spacelab.kinocms.model.ImagesEntity.ImageNews;
import java.util.ArrayList;
import java.util.Date;
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
    private Status status;
    @Column
    private String name;
    @Column
    private Date datePosting;

    @DateTimeFormat(pattern = "dd.MM.yyyy - HH:mm")
    @Column
    private Date dateCreated;
    @Column
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
