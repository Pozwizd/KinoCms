package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.repository.FilmRepository;
import spacelab.kinocms.service.FilmService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FilmServiceImp implements FilmService {

    private final FilmRepository filmRepository;


    @Override
    public List<Film> getAllFutureFilm() {
        Date currentDate = new Date(System.currentTimeMillis()); // Текущая дата

        return filmRepository.findByStartPremiereDateAfter(currentDate);
    }
    @Override
    public List<Film> getAllCurrentFilm() {
        return filmRepository.getAllCurrentFilm();
    }

    @Override
    public void saveFilm(Film film) {
        filmRepository.save(film);
    }

    @Override
    public Film getFilm(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }
    @Override
    public void updateFilm(Film film) {
        filmRepository.save(film);
    }
}
