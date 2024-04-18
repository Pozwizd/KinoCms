package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.page.MainPage;
import spacelab.kinocms.repository.MainPageRepository;
import spacelab.kinocms.service.MainPageService;

@Service
@AllArgsConstructor
public class MainPageServiceImp implements MainPageService {

    private final MainPageRepository mainPageRepository;

    private static final Logger logger = LogManager.getLogger(MainPageServiceImp.class);

    @Override
    public void saveMainPage(MainPage mainPage) {
        logger.info("Save main page: " + mainPage);
        mainPageRepository.save(mainPage);
    }

    @Override
    public MainPage getMainPage() {
        logger.info("Get default main page");
        return mainPageRepository.findById(1L).orElseThrow(() -> new UsernameNotFoundException("MainPage not found"));
    }

    @Override
    public void deleteMainPage(long id) {
        logger.info("Delete main page by id: " + id);
        mainPageRepository.deleteById(id);
    }

    @Override
    public void updateMainPage(MainPage mainPage) {
        logger.info("Update main page: " + mainPage);
        mainPageRepository.saveAndFlush(mainPage);
    }
}
