package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.repository.FilmRepository;
import spacelab.kinocms.service.FilmService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class FilmServiceImp implements FilmService {

    private final FilmRepository filmRepository;
    private final UploadFile uploadFile;
    private static final Logger logger = LogManager.getLogger(FilmServiceImp.class);


    @Override
    public List<Film> getAllFutureFilm() {
        logger.info("Get all future films");
        Date currentDate = new Date(System.currentTimeMillis());
        return filmRepository.findByStartPremiereDateAfter(currentDate);
    }
    @Override
    public List<Film> getAllCurrentFilm() {
        logger.info("Get all current films");
        return filmRepository.getAllCurrentFilm();
    }

    @Override
    public void saveFilm(Film film) {
        logger.info("Save film: " + film);
        filmRepository.save(film);
    }

    @Override
    public Film getFilm(Long id) {
        logger.info("Get film by id: " + id);
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFilm(Long id) {
        Film film = filmRepository.findById(id).orElse(null);
        if (film != null) {
            uploadFile.deleteFile(film.getMainImage());
            film.getImagesFilm().forEach(imageFilm -> uploadFile.deleteFile(imageFilm.getUrl()));
            filmRepository.deleteById(id);
        }
        logger.info("Delete film by id: " + id);
    }

    @Override
    public List<Film> getAllFilms() {
        logger.info("Get all films");
        return filmRepository.findAll();
    }
    @Override
    public void updateFilm(Film film) {
        logger.info("Update film: " + film);
        filmRepository.save(film);
    }

    @Override
    public int[] countFilmsMonth() {
        int[] countFilms = new int[8];
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.withDayOfMonth(1); // Установка даты окончания как первое число текущего месяца

        for (int i = 7; i >= 0; i--) {
            LocalDate month = endDate.minusMonths(i);
            Date startDate = Date.valueOf(month.withDayOfMonth(1));
            int year = month.getYear();
            int monthValue = month.getMonthValue();

            int filmCount = filmRepository.countFilmsInMonth(year, monthValue);
            countFilms[7 - i] = filmCount;
        }
        logger.info("Count films in month: " + Arrays.toString(countFilms));
        return countFilms;
    }
}
