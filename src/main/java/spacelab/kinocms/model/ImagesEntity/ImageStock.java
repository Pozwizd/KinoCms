package spacelab.kinocms.model.ImagesEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spacelab.kinocms.model.Stock;

@Getter
@Setter
@Entity
@Table(name = "image_stocks")
public class ImageStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String url;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "stocks_id")
    private Stock stock;

}