package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.MainPage;
import spacelab.kinocms.repository.MainPageRepository;
import spacelab.kinocms.service.MainPageService;

@Service
@AllArgsConstructor
public class MainPageServiceImp implements MainPageService {

    private final MainPageRepository mainPageRepository;

    @Override
    public void saveMainPage(MainPage mainPage) {
        mainPageRepository.save(mainPage);
    }

    @Override
    public MainPage getMainPage() {
        return mainPageRepository.findById(1L).orElseThrow(() -> new UsernameNotFoundException("MainPage not found"));
    }

    @Override
    public void deleteMainPage(long id) {
        mainPageRepository.deleteById(id);
    }

    @Override
    public void updateMainPage(MainPage mainPage) {
        mainPageRepository.saveAndFlush(mainPage);
    }
}
