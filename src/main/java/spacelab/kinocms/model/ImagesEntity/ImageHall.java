package spacelab.kinocms.model.ImagesEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spacelab.kinocms.model.Hall;

@Getter
@Setter
@Entity
@Table(name = "image_hall")
public class ImageHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String url;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hall_id")
    private Hall hall;

}