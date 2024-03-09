package spacelab.kinocms.model.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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

    @Column
    private String name;

    @OneToMany(mappedBy = "contactPage",fetch = FetchType.EAGER)
    private List<ContactCinema> contactCinemas = new ArrayList<>();

    @Column
    private String  linkVideo;

    @Column
    private Boolean status;

    @Column
    private String seoUrl;

    @Column
    private Date dateOfCreated;

    @Column
    private String seoTitle;

    @Column
    private String seoKeywords;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String seoDescription;
}