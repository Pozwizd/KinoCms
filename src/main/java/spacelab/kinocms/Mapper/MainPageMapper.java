package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Page.MainPageDto;
import spacelab.kinocms.entity.page.MainPage;
import spacelab.kinocms.service.MainPageService;

@Service
public class MainPageMapper {

    private static MainPageService mainPageService;

    public MainPageMapper( MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    public static MainPageDto toDto(MainPage mainPage) {
        MainPageDto mainPageDto = new MainPageDto();
        mainPageDto.setId(mainPage.getId());
        mainPageDto.setName(mainPage.getName());
        mainPageDto.setStatus(mainPage.getStatus());
        mainPageDto.setPhoneNumber(mainPage.getPhoneNumber());
        mainPageDto.setPhoneNumber2(mainPage.getPhoneNumber2());
        mainPageDto.setSeoText(mainPage.getSeoText());
        mainPageDto.setSeoKeywords(mainPage.getSeoKeywords());
        mainPageDto.setSeoUrl(mainPage.getSeoUrl());
        mainPageDto.setSeoTitle(mainPage.getSeoTitle());
        mainPageDto.setSeoDescription(mainPage.getSeoDescription());
        return mainPageDto;
    }

    public static MainPage toEntity(MainPageDto mainPageDto) {
        MainPage mainPage = mainPageService.getMainPage();
        mainPage.setId(mainPageDto.getId());
        mainPage.setStatus(mainPageDto.getStatus());
        mainPage.setPhoneNumber(mainPageDto.getPhoneNumber());
        mainPage.setPhoneNumber2(mainPageDto.getPhoneNumber2());
        mainPage.setSeoText(mainPageDto.getSeoText());
        mainPage.setSeoUrl(mainPageDto.getSeoUrl());
        mainPage.setSeoKeywords(mainPageDto.getSeoKeywords());
        mainPage.setSeoTitle(mainPageDto.getSeoTitle());
        mainPage.setSeoDescription(mainPageDto.getSeoDescription());
        return mainPage;
    }
}
