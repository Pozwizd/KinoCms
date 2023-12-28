package spacelab.kinocms.model.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spacelab.kinocms.model.page.Gender;
import spacelab.kinocms.model.page.Lanquage;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String surname;

    private String nickname;

    private String email;

    private String address;

    private String password;

    private String cardNumber;

    private Lanquage language;

    private Gender gender;

    private String phoneNumber;

    private Date dateOfBirth;

    private String city;
}
