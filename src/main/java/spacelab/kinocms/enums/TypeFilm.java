package spacelab.kinocms.enums;

import lombok.Getter;

@Getter
public enum TypeFilm {
    Threedimensional("3D"),
    Twodimensional("2D"),
    IMAX("IMAX");

    private final String value;
    TypeFilm(String value) {
        this.value = value;
    }
}
