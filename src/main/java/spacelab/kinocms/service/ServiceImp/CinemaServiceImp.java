package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.repository.CinemaRepository;
import spacelab.kinocms.service.CinemaService;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaServiceImp implements CinemaService {

    private final CinemaRepository cinemaRepository;

    @Override
    public void saveCinema(Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    @Override
    public void saveCinemaDto(Cinema cinema) {
        Cinema cinema1 = cinemaRepository.findById(cinema.getId()).orElse(null);
        cinema1.setName(cinema.getName());
        cinema1.setDescription(cinema.getDescription());
        cinema1.setConditions(cinema.getConditions());
        cinema1.setSeoUrl(cinema.getSeoUrl());
        cinema1.setSeoTitle(cinema.getSeoTitle());
        cinema1.setSeoDescription(cinema.getSeoDescription());
        cinema1.setSeoKeywords(cinema.getSeoKeywords());
        cinemaRepository.save(cinema1);
    }

    @Override
    public Cinema getCinema(Long id) {
        return cinemaRepository.findById(id).orElse(null);
    }

    @Override
    public Cinema getLastCinema() {
        return cinemaRepository.findTopCinemaByOrderByIdDesc();
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public void deleteCinema(long id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public void updateCinema(Cinema cinema) {
        cinemaRepository.save(cinema);
    }
}
