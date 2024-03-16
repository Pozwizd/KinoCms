package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.model.ImagesEntity.ImageFilm;
import spacelab.kinocms.repository.ImageFilmRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageFilmServiceImpTest {

    @Mock
    private ImageFilmRepository imageFilmRepository;

    @InjectMocks
    private ImageFilmServiceImp imageFilmService;

    @BeforeEach
    public void setUp() {
        reset(imageFilmRepository);
    }

    @Test
    public void saveImageFilmTest() {
        ImageFilm imageFilm = new ImageFilm();
        imageFilmService.saveImageFilm(imageFilm);
        verify(imageFilmRepository).save(imageFilm);
    }

    @Test
    public void deleteImageFilmTest() {
        Long imageFilmId = 1L;
        imageFilmService.deleteImageFilm(imageFilmId);
        verify(imageFilmRepository).deleteById(imageFilmId);
    }

    @Test
    public void getImageFilmByIdTest() {
        Long imageFilmId = 1L;
        ImageFilm expectedImageFilm = new ImageFilm();
        expectedImageFilm.setId(imageFilmId);
        when(imageFilmRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedImageFilm));
        ImageFilm actualImageFilm = imageFilmService.getImageFilmById(imageFilmId);
        assertEquals(expectedImageFilm, actualImageFilm);
    }

    @Test
    public void getLastImageFilmByFilmTest() {
        Film film = new Film();
        ImageFilm expectedImageFilm = new ImageFilm();
        when(imageFilmRepository.findTopImageFilmByFilmOrderByIdDesc(Mockito.any(Film.class))).thenReturn(expectedImageFilm);
        ImageFilm actualImageFilm = imageFilmService.getLastImageFilmByFilm(film);
        assertEquals(expectedImageFilm, actualImageFilm);
    }

    @Test
    public void getAllImageFilmTest() {
        List<ImageFilm> expectedImageFilms = new ArrayList<>();
        when(imageFilmRepository.findAll()).thenReturn(expectedImageFilms);
        List<ImageFilm> actualImageFilms = imageFilmService.getAllImageFilm();
        assertEquals(expectedImageFilms, actualImageFilms);
    }

    @Test
    public void getAllImageFilmByFilmTest() {
        Film film = new Film();
        List<ImageFilm> expectedImageFilms = new ArrayList<>();
        when(imageFilmRepository.findAllImageFilmByFilm(Mockito.any(Film.class))).thenReturn(expectedImageFilms);
        List<ImageFilm> actualImageFilms = imageFilmService.getAllImageFilmByFilm(film);
        assertEquals(expectedImageFilms, actualImageFilms);
    }

    @Test
    public void updateImageFilmTest() {
        ImageFilm imageFilm = new ImageFilm();
        imageFilmService.updateImageFilm(imageFilm);
        verify(imageFilmRepository).saveAndFlush(imageFilm);
    }
}