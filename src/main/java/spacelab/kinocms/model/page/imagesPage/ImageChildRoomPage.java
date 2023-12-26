package spacelab.kinocms.model.page.imagesPage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.page.ChildRoomPage;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_child_room_page")
public class ImageChildRoomPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "child_room_page_id")
    private ChildRoomPage childRoomPage;

}