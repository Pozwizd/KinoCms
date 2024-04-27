package spacelab.kinocms.Dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import spacelab.kinocms.enums.Gender;
import spacelab.kinocms.enums.Lanquage;
import spacelab.kinocms.model.Role;
import spacelab.kinocms.model.User;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link User}
 */
@Data
public class UserDto{
    Long id;
    @NotEmpty(message = "Пожалуйста, введите ваше имя")
    String name;
    String surname;
    String nickname;
    @Email(message = "Введите корректный адрес электронной почты")
    @NotEmpty(message = "Пожалуйста, введите ваш email")
    String email;
    String address;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$",
            message = "Пароль должен содержать хотя бы одну строчную и одну заглавную букву")
    @NotEmpty
    String password;
    String cardNumber;
    Lanquage language;
    Gender gender;
    String phoneNumber;
    Date dateOfBirth;
    String city;
    Role role;
}