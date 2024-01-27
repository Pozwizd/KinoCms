package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;

import java.util.List;

@Service
public interface CinemaService {

    public void saveCinema(Cinema cinema);

    public void saveCinemaDto(Cinema cinema);
    public Cinema getCinema(long id);

    public Cinema getLastCinema();

    public List<Cinema> getAllCinemas();

    public void deleteCinema(long id);

    public void updateCinema(Cinema cinema);
}
