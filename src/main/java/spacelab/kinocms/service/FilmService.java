package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Film;

import java.sql.Date;
import java.util.List;

@Service
public interface FilmService {

    public List<Film> getAllFutureFilm();
    public List<Film> getAllCurrentFilm();
    public void saveFilm(Film film);
    public Film getFilm(Long id);
    public void deleteFilm(Long id);
    public List<Film> getAllFilms();
    public void updateFilm(Film film);
}
