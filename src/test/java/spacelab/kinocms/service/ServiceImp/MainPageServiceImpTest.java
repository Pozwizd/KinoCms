package spacelab.kinocms.service.ServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import spacelab.kinocms.entity.page.MainPage;
import spacelab.kinocms.repository.MainPageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainPageServiceImpTest {

    @Mock
    private MainPageRepository mainPageRepository;

    @InjectMocks
    private MainPageServiceImp mainPageService;

    @BeforeEach
    public void setUp() {
        reset(mainPageRepository);
    }


    @Test
    void saveMainPageTest() {
        MainPage mainPage = new MainPage();
        mainPageService.saveMainPage(mainPage);
        verify(mainPageRepository, times(1)).save(mainPage);
    }

    @Test
    void getMainPageTest() {
        long id = 1L;
        MainPage expectedMainPage = new MainPage();
        when(mainPageRepository.findById(id)).thenReturn(Optional.of(expectedMainPage));
        MainPage actualMainPage = mainPageService.getMainPage();
        assertEquals(expectedMainPage, actualMainPage);
    }

    @Test
    void getMainPageTestNotFound() {
        long id = 1L;
        when(mainPageRepository.findById(id)).thenReturn(Optional.empty());
        try {
            mainPageService.getMainPage();
        } catch (UsernameNotFoundException e) {
            assertEquals("MainPage not found", e.getMessage());
        }
    }

    @Test
    void deleteMainPageTest() {
        long id = 1L;
        mainPageService.deleteMainPage(id);
        verify(mainPageRepository, times(1)).deleteById(id);
    }

    @Test
    void updateMainPageTest() {
        MainPage mainPage = new MainPage();
        mainPageService.updateMainPage(mainPage);
        verify(mainPageRepository, times(1)).saveAndFlush(mainPage);
    }
}