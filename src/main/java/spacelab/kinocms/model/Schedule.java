package spacelab.kinocms.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
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
    private Double price;

    @Column
    private Double priceVip;

}
