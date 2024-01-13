package spacelab.kinocms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.enums.Status;
import spacelab.kinocms.model.ImagesEntity.ImageStocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stocks {

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
    @Column
    private Date dateCreated;
    @Column
    private String description;
    @Column
    private String mainImage;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stocks")
    private List<ImageStocks> imagesStocks = new ArrayList<>();
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
