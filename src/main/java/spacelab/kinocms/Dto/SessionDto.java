package spacelab.kinocms.Dto;

import lombok.Data;
import spacelab.kinocms.entity.Session;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO for {@link Session}
 */
@Data
public class SessionDto {
    public SessionDto() {
    }

    @NotEmpty
    private String id;

    @NotEmpty(message = "Пожалуйста, введите время")
    @NotNull
    private String timeSession;
    @NotNull
    @NotEmpty(message = "Пожалуйста, введите фильм")
    private String filmId;

    @NotEmpty(message = "Пожалуйста, введите кинотеатр")
    private String cinemaId;

    @NotEmpty(message = "Пожалуйста, введите зал")
    private String hallId;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @Positive
    private Double priceVip;
}