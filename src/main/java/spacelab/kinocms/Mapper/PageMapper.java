package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Page.ImagePageDto;
import spacelab.kinocms.Dto.Page.PageDto;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.page.ImagePage;
import spacelab.kinocms.entity.page.Page;
import spacelab.kinocms.service.ImagePageService;
import spacelab.kinocms.service.PageService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PageMapper {

    private final PageService pageService;
    private final ImagePageService imagePageService;
    private final UploadFile uploadFile;

    public PageMapper(PageService pageService, ImagePageService imagePageService, UploadFile uploadFile) {
        this.pageService = pageService;
        this.imagePageService = imagePageService;
        this.uploadFile = uploadFile;
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

    public Page toEntity(PageDto filmDto) {
        Page pageFromDb = pageService.getPage(filmDto.getId());
        if (pageFromDb.getId() == null){
            pageFromDb.setDateOfCreated(new Date(System.currentTimeMillis()));
        }
        pageFromDb.setName(filmDto.getName());
        pageFromDb.setStatus(filmDto.getStatus());
        pageFromDb.setDescription(filmDto.getDescription());
        pageFromDb.setSeoUrl(filmDto.getSeoUrl());
        pageFromDb.setSeoTitle(filmDto.getSeoTitle());
        pageFromDb.setSeoKeywords(filmDto.getSeoKeywords());
        pageFromDb.setSeoDescription(filmDto.getSeoDescription());



        pageService.updatePage(pageFromDb);

        if (filmDto.getImagesAboutCinema() != null) {
            List<Long> ids = filmDto
                    .getImagesAboutCinema().stream().map(ImagePageDto::getId).filter(Objects::nonNull).toList();

            pageFromDb.getImagesAboutCinema().stream().filter(imageFilm -> !ids.contains(imageFilm.getId()))
                    .forEach(imagePage -> imagePageService.deleteImagePage(imagePage.getId()));
            pageFromDb.getImagesAboutCinema()
                    .removeIf(imageFilm -> !ids.contains(imageFilm.getId()));
        }

        if (filmDto.getMainImage().getSize() != 0) {
            pageFromDb.setMainImage(uploadFile.uploadFile(filmDto.getMainImage(), pageFromDb.getMainImage()));
        }

        if (filmDto.getImagesAboutCinema() != null) {
            for (ImagePageDto imagePageDto : filmDto.getImagesAboutCinema()) {
                ImagePage imageFilm = imagePageDto.getId() == null ? new ImagePage() :  imagePageService.getImagePage(imagePageDto.getId());
                imageFilm.setId(imagePageDto.getId());
                if (!imagePageDto.getUrl().isEmpty()) {
                    imageFilm.setUrl(uploadFile.uploadFile(imagePageDto.getUrl(), imageFilm.getUrl()));
                }
                imageFilm.setPage(pageFromDb);
                imagePageService.updateImagePage(imageFilm);
                pageFromDb.getImagesAboutCinema().add(imageFilm);
            }
        } else {
            if (pageFromDb.getImagesAboutCinema() != null) {
                for (ImagePage imagePage : pageFromDb.getImagesAboutCinema()) {
                    imagePageService.deleteImagePage(imagePage.getId());
                }
                pageFromDb.setImagesAboutCinema(new ArrayList<>());
            }
        }

        return pageFromDb;
    }

}
