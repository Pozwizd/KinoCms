package spacelab.kinocms.model.ImagesEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spacelab.kinocms.model.News;

@Getter
@Setter
@Entity
@Table(name = "image_news")
public class ImageNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

}