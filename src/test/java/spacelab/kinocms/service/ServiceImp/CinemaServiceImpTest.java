package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.repository.CinemaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CinemaServiceImpTest {

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private CinemaServiceImp cinemaService;


    @BeforeEach
    public void setUp() {
        reset(cinemaRepository);
    }
    @Test
    void testSaveCinema() {
        Cinema cinema = new Cinema();
        cinemaService.saveCinema(cinema);
        verify(cinemaRepository).save(cinema);
    }

    @Test
    void testSaveCinemaDto() {
        Cinema cinema = new Cinema();
        cinema.setId(1L);
        Cinema existingCinema = new Cinema();
        when(cinemaRepository.findById(cinema.getId())).thenReturn(Optional.of(existingCinema));
        cinemaService.saveCinemaDto(cinema);
        verify(cinemaRepository).save(existingCinema);
    }

    @Test
    void testGetCinema() {
        Long id = 1L;
        Cinema expectedCinema = new Cinema();
        when(cinemaRepository.findById(id)).thenReturn(Optional.of(expectedCinema));
        Cinema result = cinemaService.getCinema(id);
        assertEquals(expectedCinema, result);
    }

    @Test
    void testGetLastCinema() {
        Cinema expectedCinema = new Cinema();
        when(cinemaRepository.findTopCinemaByOrderByIdDesc()).thenReturn(expectedCinema);
        Cinema result = cinemaService.getLastCinema();
        assertEquals(expectedCinema, result);
    }

    @Test
    void testGetAllCinemas() {
        List<Cinema> expectedCinemas = Arrays.asList(new Cinema(), new Cinema(), new Cinema());
        when(cinemaRepository.findAll()).thenReturn(expectedCinemas);
        List<Cinema> result = cinemaService.getAllCinemas();
        assertEquals(expectedCinemas, result);
    }

    @Test
    void testDeleteCinema() {
        long id = 1L;
        cinemaService.deleteCinema(id);
        verify(cinemaRepository).deleteById(id);
    }

    @Test
    void testUpdateCinema() {
        Cinema cinema = new Cinema();
        cinemaService.updateCinema(cinema);
        verify(cinemaRepository).save(cinema);
    }
}