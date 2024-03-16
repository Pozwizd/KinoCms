package spacelab.kinocms.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import spacelab.kinocms.enums.Gender;
import spacelab.kinocms.enums.Lanquage;
import spacelab.kinocms.model.User;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link User}
 */
@Data
public class UserDto implements Serializable {
    Long id;
    @NotEmpty(message = "Пожалуйста, введите ваше имя")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z0-9]+$", message = "Имя может содержать только буквы и цифры")
    String name;
    String surname;
    String nickname;
    @Email(message = "Введите корректный адрес электронной почты")
    @NotEmpty(message = "Пожалуйста, введите ваш email")
    String email;
    String address;
    @Pattern(message = "Пароль должен содержать хотя бы одну строчную и одну заглавную букву", regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$")
    @NotEmpty(message = "Пожалуйста, введите пароль")
    String password;
    String cardNumber;
    Lanquage language;
    Gender gender;
    String phoneNumber;
    Date dateOfBirth;
    String city;
}