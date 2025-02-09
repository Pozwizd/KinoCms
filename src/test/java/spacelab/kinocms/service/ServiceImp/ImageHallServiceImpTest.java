package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spacelab.kinocms.entity.Hall;
import spacelab.kinocms.entity.ImagesEntity.ImageHall;
import spacelab.kinocms.repository.ImageHallRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageHallServiceImpTest {

    @Mock
    private ImageHallRepository imageHallRepository;

    @InjectMocks
    private ImageHallServiceImp imageHallService;

    @BeforeEach
    public void setUp() {
        reset(imageHallRepository);
    }


    @Test
    public void saveImageHallTest() {
        ImageHall imageHall = new ImageHall();
        imageHallService.saveImageHall(imageHall);
        verify(imageHallRepository).save(imageHall);
    }

    @Test
    public void getImageHallTest() {
        Long imageHallId = 1L;
        ImageHall expectedImageHall = new ImageHall();
        expectedImageHall.setId(imageHallId);
        when(imageHallRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedImageHall));
        ImageHall actualImageHall = imageHallService.getImageHall(imageHallId);
        assertEquals(expectedImageHall, actualImageHall);
    }

    @Test
    public void getLastImageHallByHallTest() {
        Hall hall = new Hall();
        ImageHall expectedImageHall = new ImageHall();
        when(imageHallRepository.findTopImageHallByHallOrderByIdDesc(Mockito.any(Hall.class))).thenReturn(expectedImageHall);
        ImageHall actualImageHall = imageHallService.getLastImageHallByHall(hall);
        assertEquals(expectedImageHall, actualImageHall);
    }

    @Test
    public void getAllImageHallTest() {
        List<ImageHall> expectedImageHalls = new ArrayList<>();
        when(imageHallRepository.findAll()).thenReturn(expectedImageHalls);
        List<ImageHall> actualImageHalls = imageHallService.getAllImageHall();
        assertEquals(expectedImageHalls, actualImageHalls);
    }

    @Test
    public void getAllImageHallByHallTest() {
        Hall hall = new Hall();
        List<ImageHall> expectedImageHalls = new ArrayList<>();
        when(imageHallRepository.findAllImageHallByHall(Mockito.any(Hall.class))).thenReturn(expectedImageHalls);
        List<ImageHall> actualImageHalls = imageHallService.getAllImageHallByHall(hall);
        assertEquals(expectedImageHalls, actualImageHalls);
    }

    @Test
    public void updateImageHallTest() {
        ImageHall imageHall = new ImageHall();
        imageHallService.updateImageHall(imageHall);
        verify(imageHallRepository).save(imageHall);
    }

    @Test
    public void deleteImageHallTest() {
        long imageHallId = 1L;
        imageHallService.deleteImageHall(imageHallId);
        verify(imageHallRepository).deleteById(imageHallId);
    }
}