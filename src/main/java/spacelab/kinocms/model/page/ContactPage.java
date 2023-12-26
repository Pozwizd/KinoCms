package spacelab.kinocms.model.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.Status;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact_page")
public class ContactPage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "contactPage")
    private List<ContactCinema> contactCinemas = new ArrayList<>();

    @Column
    private String  linkVideo;

    @Column
    private String seoUrl;

    @Column
    private String seoTitle;

    @Column
    private String seoKeywords;

    @Column
    private String seoDescription;
}