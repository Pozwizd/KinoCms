package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.repository.FilmRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceImpTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmServiceImp filmService;

    @BeforeEach
    public void setUp() {
        reset(filmRepository);
    }

    @Test
    public void getAllFutureFilmTest() {
        List<Film> expectedFilms = new ArrayList<>();
        Date currentDate = new Date(System.currentTimeMillis());
        when(filmRepository.findByStartPremiereDateAfter(Mockito.any(Date.class))).thenReturn(expectedFilms);
        List<Film> actualFilms = filmService.getAllFutureFilm();
        assertEquals(expectedFilms, actualFilms);
    }

    @Test
    public void getAllCurrentFilmTest() {
        List<Film> expectedFilms = new ArrayList<>();
        when(filmRepository.getAllCurrentFilm()).thenReturn(expectedFilms);
        List<Film> actualFilms = filmService.getAllCurrentFilm();
        assertEquals(expectedFilms, actualFilms);
    }

    @Test
    public void saveFilmTest() {
        Film film = new Film();
        filmService.saveFilm(film);
        verify(filmRepository).save(film);
    }

    @Test
    public void getFilmTest() {
        Long filmId = 1L;
        Film expectedFilm = new Film();
        expectedFilm.setId(filmId);
        when(filmRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(expectedFilm));
        Film actualFilm = filmService.getFilm(filmId);
        assertEquals(expectedFilm, actualFilm);
    }

    @Test
    public void deleteFilmTest() {
        Long filmId = 1L;
        filmService.deleteFilm(filmId);
        verify(filmRepository).deleteById(filmId);
    }

    @Test
    public void getAllFilmsTest() {
        List<Film> expectedFilms = new ArrayList<>();
        when(filmRepository.findAll()).thenReturn(expectedFilms);
        List<Film> actualFilms = filmService.getAllFilms();
        assertEquals(expectedFilms, actualFilms);
    }

    @Test
    public void updateFilmTest() {
        Film film = new Film();
        filmService.updateFilm(film);
        verify(filmRepository).save(film);
    }

    @Test
    public void countFilmsMonthTest() {
        int[] expectedCountFilms = new int[8];
        Arrays.fill(expectedCountFilms, 1);
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.withDayOfMonth(1);
        when(filmRepository.countFilmsInMonth(Mockito.anyInt(),
                Mockito.anyInt())).thenReturn(1);
        int[] actualCountFilms = filmService.countFilmsMonth();
        assertArrayEquals(expectedCountFilms, actualCountFilms);
    }
}