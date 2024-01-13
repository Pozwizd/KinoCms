package spacelab.kinocms.model.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact_cinema")
public class ContactCinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private String location;

    @Column(name = "logo")
    private String logo;

    @ManyToOne
    @JoinColumn(name = "contact_page_id")
    private ContactPage contactPage;

}