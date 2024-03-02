package spacelab.kinocms.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Type;
import java.sql.Types;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    Тип данных для времени должен быть типом datatime
    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeSession;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film filmId;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinemaId;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hallId;

    @Column
    private Double price = 0.0;

    @Column
    private Double priceVip = 0.0;

}
