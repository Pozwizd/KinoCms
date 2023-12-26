package spacelab.kinocms.model.page.imagesPage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.page.AdvertisingPage;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_advertising_page")
public class ImageAdvertisingPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "advertising_page_id")
    private AdvertisingPage advertisingPage;

}