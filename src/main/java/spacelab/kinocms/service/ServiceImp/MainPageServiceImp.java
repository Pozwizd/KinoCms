package spacelab.kinocms.service.ServiceImp;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.MainPage;
import spacelab.kinocms.repository.MainPageRepository;
import spacelab.kinocms.service.MainPageService;

@Service
public class MainPageServiceImp implements MainPageService {

    private final MainPageRepository mainPageRepository;

    public MainPageServiceImp(MainPageRepository mainPageRepository) {
        this.mainPageRepository = mainPageRepository;
    }


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
        MainPage mainPage1 = mainPageRepository.findById(1L).orElseThrow(() -> new UsernameNotFoundException("MainPage not found"));
        mainPage1.setId(1L);
        mainPage1.setPhoneNumber(mainPage.getPhoneNumber());
        mainPage1.setPhoneNumber2(mainPage.getPhoneNumber2());
        mainPage1.setSeoText(mainPage.getSeoText());
        mainPage1.setSeoUrl(mainPage.getSeoUrl());
        mainPage1.setStatus(mainPage.isStatus());
        mainPage1.setSeoKeywords(mainPage.getSeoKeywords());
        mainPage1.setSeoDescription(mainPage.getSeoDescription());
        mainPageRepository.save(mainPage1);
    }
}
