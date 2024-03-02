package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Page.MainPageDto;
import spacelab.kinocms.model.page.MainPage;
import spacelab.kinocms.repository.MainPageRepository;

@Service
public class MainPageMapper {

    private static MainPageRepository mainPageRepository;

    public MainPageMapper(MainPageRepository mainPageRepository) {
        this.mainPageRepository = mainPageRepository;
    }

    public static MainPageDto toDto(MainPage mainPage) {
        MainPageDto mainPageDto = new MainPageDto();
        mainPageDto.setId(mainPage.getId());
        mainPageDto.setName(mainPage.getName());
        mainPageDto.setStatus(mainPage.getStatus());
        mainPageDto.setPhoneNumber(mainPage.getPhoneNumber());
        mainPageDto.setPhoneNumber2(mainPage.getPhoneNumber2());
        mainPageDto.setSeoText(mainPage.getSeoText());
        mainPageDto.setSeoUrl(mainPage.getSeoUrl());
        mainPageDto.setSeoTitle(mainPage.getSeoTitle());
        mainPageDto.setSeoDescription(mainPage.getSeoDescription());
        return mainPageDto;
    }

    public static MainPage toEntity(MainPageDto mainPageDto) {
        MainPage mainPage = new MainPage();
        mainPage.setId(mainPageDto.getId());
        mainPage.setDateOfCreated(mainPageRepository.findCreationDateById(mainPageDto.getId()));
        mainPage.setName(mainPageRepository.findNameById(mainPageDto.getId()));
        mainPage.setStatus(mainPageDto.getStatus());
        mainPage.setPhoneNumber(mainPageDto.getPhoneNumber());
        mainPage.setPhoneNumber2(mainPageDto.getPhoneNumber2());
        mainPage.setSeoText(mainPageDto.getSeoText());
        mainPage.setSeoUrl(mainPageDto.getSeoUrl());
        mainPage.setSeoTitle(mainPageDto.getSeoTitle());
        mainPage.setSeoDescription(mainPageDto.getSeoDescription());
        return mainPage;
    }
}
