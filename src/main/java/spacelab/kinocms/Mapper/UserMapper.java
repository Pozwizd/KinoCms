package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.User;
import spacelab.kinocms.Dto.UserDto;
import spacelab.kinocms.service.UserService;

@Service
public class UserMapper {
    private final UserService userService;

    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setNickname(user.getNickname());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPassword(user.getPassword());
        userDto.setCardNumber(user.getCardNumber());
        userDto.setLanguage(user.getLanguage());
        userDto.setGender(user.getGender());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setCity(user.getCity());
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User user = userService.getUser(userDto.getId());
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getPassword());
        user.setCardNumber(userDto.getCardNumber());
        user.setLanguage(userDto.getLanguage());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setCity(userDto.getCity());
        return user;
    }
}
