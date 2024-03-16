package spacelab.kinocms.Dto;

import lombok.Data;
import spacelab.kinocms.model.Session;

import java.io.Serializable;

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