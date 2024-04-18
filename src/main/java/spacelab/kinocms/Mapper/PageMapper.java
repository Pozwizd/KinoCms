package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Page.PageDto;
import spacelab.kinocms.model.page.Page;
import spacelab.kinocms.service.PageService;
import spacelab.kinocms.service.UserService;

@Service
public class PageMapper {

    private final PageService pageService;

    public PageMapper(PageService pageService) {
        this.pageService = pageService;
    }


    public PageDto toDto(Page page) {
        PageDto pageDto = new PageDto();
        pageDto.setId(page.getId());
        pageDto.setStatus(page.getStatus());
        pageDto.setName(page.getName());
        pageDto.setDescription(page.getDescription());
        pageDto.setSeoUrl(page.getSeoUrl());
        pageDto.setSeoTitle(page.getSeoTitle());
        pageDto.setSeoKeywords(page.getSeoKeywords());
        pageDto.setSeoDescription(page.getSeoDescription());
        return pageDto;
    }

    public Page toEntity(PageDto pageDto) {
        Page page = pageService.getPage(pageDto.getId());
        if (page == null) {
            page = new Page();
        }
        page.setId(pageDto.getId());
        page.setStatus(pageDto.getStatus());
        page.setName(pageDto.getName());
        page.setDescription(pageDto.getDescription());
        page.setSeoUrl(pageDto.getSeoUrl());
        page.setSeoTitle(pageDto.getSeoTitle());
        page.setSeoKeywords(pageDto.getSeoKeywords());
        page.setSeoDescription(pageDto.getSeoDescription());

        return page;
    }

}
