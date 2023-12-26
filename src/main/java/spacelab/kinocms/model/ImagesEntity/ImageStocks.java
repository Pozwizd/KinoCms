package spacelab.kinocms.model.ImagesEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spacelab.kinocms.model.Stocks;

@Getter
@Setter
@Entity
@Table(name = "image_stocks")
public class ImageStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "stocks_id")
    private Stocks stocks;

}