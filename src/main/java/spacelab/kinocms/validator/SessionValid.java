package spacelab.kinocms.validator;

import lombok.Data;

@Data
public class SessionValid {
    private Long id;
    private Boolean timeSession = true;
    private Boolean filmId = true;
    private Boolean cinemaId = true;
    private Boolean hallId = true;
    private Boolean price = true;
    private Boolean priceVip = true;


}
