package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.repository.CinemaRepository;
import spacelab.kinocms.service.CinemaService;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaServiceImp implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private static final Logger logger = LogManager.getLogger(CinemaServiceImp.class);

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
        logger.info("Save cinema: " + cinema);
    }

    @Override
    public Cinema getCinema(Long id) {
        logger.info("Get cinema by id: " + id);
        return cinemaRepository.findById(id).orElse(null);
    }

    @Override
    public Cinema getLastCinema() {
        logger.info("Get last cinema");
        return cinemaRepository.findTopCinemaByOrderByIdDesc();
    }

    @Override
    public List<Cinema> getAllCinemas() {
        logger.info("Get all cinemas");
        return cinemaRepository.findAll();
    }

    @Override
    public void deleteCinema(long id) {
        logger.info("Delete cinema by id: " + id);
        cinemaRepository.deleteById(id);
    }

    @Override
    public void updateCinema(Cinema cinema) {
        cinemaRepository.save(cinema);
        logger.info("Update cinema: " + cinema);
    }
}
