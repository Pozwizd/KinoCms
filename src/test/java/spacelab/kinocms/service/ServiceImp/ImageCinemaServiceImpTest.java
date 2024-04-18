package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;
import spacelab.kinocms.repository.ImageCinemaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageCinemaServiceImpTest {

    @Mock
    private ImageCinemaRepository imageCinemaRepository;

    @InjectMocks
    private ImageCinemaServiceImp imageCinemaService;

    @BeforeEach
    public void setUp() {
        reset(imageCinemaRepository);
    }

    @Test
    public void saveImageCinemaTest() {
        ImageCinema imageCinema = new ImageCinema();
        imageCinemaService.saveImageCinema(imageCinema);
        verify(imageCinemaRepository).save(imageCinema);
    }

    @Test
    public void getImageCinemaTest() {
        Long imageCinemaId = 1L;
        ImageCinema expectedImageCinema = new ImageCinema();
        expectedImageCinema.setId(imageCinemaId);
        when(imageCinemaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedImageCinema));
        ImageCinema actualImageCinema = imageCinemaService.getImageCinema(imageCinemaId);
        assertEquals(expectedImageCinema, actualImageCinema);
    }

    @Test
    public void getLastImageCinemaByCinemaTest() {
        Cinema cinema = new Cinema();
        cinema.setId(1L);
        ImageCinema expectedImageCinema = new ImageCinema();
        when(imageCinemaRepository.findTopImageCinemaByCinemaOrderByIdDesc(Mockito.any(Cinema.class))).thenReturn(expectedImageCinema);
        ImageCinema actualImageCinema = imageCinemaService.getLastImageCinemaByCinema(cinema);
        assertEquals(expectedImageCinema, actualImageCinema);
    }

    @Test
    public void getAllImageCinemaTest() {
        List<ImageCinema> expectedImageCinemas = new ArrayList<>();
        when(imageCinemaRepository.findAll()).thenReturn(expectedImageCinemas);
        List<ImageCinema> actualImageCinemas = imageCinemaService.getAllImageCinema();
        assertEquals(expectedImageCinemas, actualImageCinemas);
    }

    @Test
    public void getAllImageCinemaByCinemaTest() {
        Cinema cinema = new Cinema();
        List<ImageCinema> expectedImageCinemas = new ArrayList<>();
        when(imageCinemaRepository.findAllByCinema(Mockito.any(Cinema.class))).thenReturn(expectedImageCinemas);
        List<ImageCinema> actualImageCinemas = imageCinemaService.getAllImageCinemaByCinema(cinema);
        assertEquals(expectedImageCinemas, actualImageCinemas);
    }

    @Test
    public void updateImageCinemaTest() {
        ImageCinema imageCinema = new ImageCinema();
        imageCinemaService.updateImageCinema(imageCinema);
        verify(imageCinemaRepository).save(imageCinema);
    }

    @Test
    public void deleteImageCinemaTest() {
        long imageCinemaId = 1L;
        imageCinemaService.deleteImageCinema(imageCinemaId);
        verify(imageCinemaRepository).deleteById(imageCinemaId);
    }
}