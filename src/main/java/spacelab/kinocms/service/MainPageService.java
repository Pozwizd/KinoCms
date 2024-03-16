package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.MainPage;

@Service
public interface MainPageService {

    public void saveMainPage(MainPage mainPage);
    public MainPage getMainPage();
    public void deleteMainPage(long id);
    public void updateMainPage(MainPage mainPage);

}
