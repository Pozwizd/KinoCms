package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.Images.ImageCinemaDto;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.Cinema;
import spacelab.kinocms.Dto.CinemaDto;
import spacelab.kinocms.entity.ImagesEntity.ImageCinema;
import spacelab.kinocms.service.CinemaService;
import spacelab.kinocms.service.ImageCinemaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CinemaMapper {
    private final CinemaService cinemaService;
    private final UploadFile uploadFile;
    private final ImageCinemaService imageCinemaService;

    public CinemaMapper(CinemaService cinemaService, UploadFile uploadFile, ImageCinemaService imageCinemaService) {
        this.cinemaService = cinemaService;
        this.uploadFile = uploadFile;
        this.imageCinemaService = imageCinemaService;
    }

    public CinemaDto toDto(Cinema entity) {
        CinemaDto dto = new CinemaDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setConditions(entity.getConditions());
        dto.setHalls(entity.getHalls());
        dto.setSeoUrl(entity.getSeoUrl());
        dto.setSeoTitle(entity.getSeoTitle());
        dto.setSeoKeywords(entity.getSeoKeywords());
        dto.setSeoDescription(entity.getSeoDescription());
        return dto;
    }

    public Cinema toEntity(CinemaDto cinemaDto) {

        Cinema cinemaFromDb = cinemaService.getCinema(cinemaDto.getId());
        cinemaFromDb.setName(cinemaDto.getName());
        cinemaFromDb.setDescription(cinemaDto.getDescription());
        cinemaFromDb.setSeoUrl(cinemaDto.getSeoUrl());
        cinemaFromDb.setSeoTitle(cinemaDto.getSeoTitle());
        cinemaFromDb.setSeoKeywords(cinemaDto.getSeoKeywords());
        cinemaFromDb.setSeoDescription(cinemaDto.getSeoDescription());
        cinemaFromDb.setConditions(cinemaDto.getConditions());

        cinemaService.saveCinema(cinemaFromDb);
        if(cinemaDto.getLogoPath() != null) {
            if (cinemaDto.getLogoPath().getSize() != 0) {
                cinemaFromDb.setLogoPath(uploadFile.uploadFile(cinemaDto.getLogoPath(), cinemaFromDb.getLogoPath()));
            }
        }
        if(cinemaDto.getTopBanner() != null) {
            if (cinemaDto.getTopBanner().getSize() != 0) {
                cinemaFromDb.setTopBanner(uploadFile.uploadFile(cinemaDto.getTopBanner(), cinemaFromDb.getTopBanner()));
            }
        }


        if (cinemaDto.getImagesCinema() != null) {
            List<Long> ids = cinemaDto
                    .getImagesCinema().stream().map(ImageCinemaDto::getId).filter(Objects::nonNull).toList();

            cinemaFromDb.getImagesCinema().stream().filter(imageFilm -> !ids.contains(imageFilm.getId()))
                    .forEach(imageFilm -> imageCinemaService.deleteImageCinema(imageCinemaService.getImageCinema(imageFilm.getId()).getId()));
            cinemaFromDb.getImagesCinema()
                    .removeIf(imageCinema -> !ids.contains(imageCinema.getId()));
        }



        if (cinemaDto.getImagesCinema() != null) {
            for (ImageCinemaDto imageCinemaDto : cinemaDto.getImagesCinema()) {
                ImageCinema imageCinema = imageCinemaDto.getId() == null ? new ImageCinema() :
                        imageCinemaService.getImageCinema(imageCinemaDto.getId());
                imageCinema.setId(imageCinemaDto.getId());
                if (!imageCinemaDto.getUrl().isEmpty()) {
                    imageCinema.setUrl(uploadFile.uploadFile(imageCinemaDto.getUrl(), imageCinema.getUrl()));
                }
                imageCinema.setCinema(cinemaFromDb);
                imageCinemaService.saveImageCinema(imageCinema);
                cinemaFromDb.getImagesCinema().add(imageCinema);
            }
        } else {
            if (cinemaFromDb.getImagesCinema() != null) {
                for (ImageCinema imageCinema : cinemaFromDb.getImagesCinema()) {
                    imageCinemaService.deleteImageCinema(imageCinema.getId());
                }
                cinemaFromDb.setImagesCinema(new ArrayList<>());
            }
        }

        return cinemaFromDb;
    }
}
