package spacelab.kinocms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spacelab.kinocms.enums.Gender;
import spacelab.kinocms.enums.Lanquage;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
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

    @Enumerated(EnumType.STRING)
    private Lanquage language;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private Date dateOfBirth;

    private Date dateOfRegistration;

    private String city;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the authorities for this user somehow
        return Arrays.asList(() -> "ROLE_USER");
    }

    @Override
    public String getUsername() {
        // Return the username (email)
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
