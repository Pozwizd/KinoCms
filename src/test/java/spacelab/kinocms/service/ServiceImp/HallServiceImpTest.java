package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.repository.HallRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HallServiceImpTest {

    @Mock
    private HallRepository hallRepository;

    @InjectMocks
    private HallServiceImp hallService;

    @BeforeEach
    public void setUp() {
        reset(hallRepository);
    }
    @Test
    public void saveHallTest() {
        Hall hall = new Hall();
        hallService.saveHall(hall);
        verify(hallRepository).save(hall);
    }

    @Test
    public void getHallTest() {
        Long hallId = 1L;
        Hall expectedHall = new Hall();
        expectedHall.setId(hallId);
        when(hallRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedHall));
        Hall actualHall = hallService.getHall(hallId);
        assertEquals(expectedHall, actualHall);
    }

    @Test
    public void getAllHallTest() {
        List<Hall> expectedHalls = new ArrayList<>();
        when(hallRepository.findAll()).thenReturn(expectedHalls);
        List<Hall> actualHalls = hallService.getAllHall();
        assertEquals(expectedHalls, actualHalls);
    }

    @Test
    public void getHallByCinemaTest() {
        Cinema cinema = new Cinema();
        List<Hall> expectedHalls = new ArrayList<>();
        when(hallRepository.findAllByCinema(Mockito.any(Cinema.class))).thenReturn(expectedHalls);
        List<Hall> actualHalls = hallService.getHallByCinema(cinema);
        assertEquals(expectedHalls, actualHalls);
    }

    @Test
    public void updateHallTest() {
        Hall hall = new Hall();
        hallService.updateHall(hall);
        verify(hallRepository).save(hall);
    }

    @Test
    public void deleteHallTest() {
        long hallId = 1L;
        hallService.deleteHall(hallId);
        verify(hallRepository).deleteById(hallId);
    }

    @Test
    public void saveHallPageTest() {
        Hall hall = new Hall();
        hall.setId(1L);
        when(hallRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(hall));
        hallService.saveHallPage(hall);
        verify(hallRepository).save(hall);
    }
}