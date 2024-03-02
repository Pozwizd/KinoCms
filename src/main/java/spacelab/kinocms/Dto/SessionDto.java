package spacelab.kinocms.Dto;

import lombok.Data;
import lombok.Value;
import spacelab.kinocms.Dto.FilmDto;
import spacelab.kinocms.model.Session;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Session}
 */
@Data
public class SessionDto implements Serializable {
    String id;
    String timeSession;
    String filmId;
    String cinemaId;
    String hallId;
    String price;
    String priceVip;
}