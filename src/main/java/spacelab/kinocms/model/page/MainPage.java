package spacelab.kinocms.model.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.enums.Status;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mainPage")
public class MainPage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;


    @Column
    private Boolean status;

    @Column
    private String phoneNumber;

    @Column
    private String phoneNumber2;

    @Column
    private String seoText;

    @Column
    private Date dateOfCreated;

    @Column
    private String seoUrl;

    @Column
    private String seoTitle;

    @Column
    private String seoKeywords;

    @Column
    private String seoDescription;
}