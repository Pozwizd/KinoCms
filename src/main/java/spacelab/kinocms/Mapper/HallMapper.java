package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.HallDto;
import spacelab.kinocms.Dto.Images.ImageCinemaDto;
import spacelab.kinocms.Dto.Images.ImageHallDto;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.model.ImagesEntity.ImageCinema;
import spacelab.kinocms.model.ImagesEntity.ImageHall;
import spacelab.kinocms.service.CinemaService;
import spacelab.kinocms.service.HallService;
import spacelab.kinocms.service.ImageHallService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HallMapper {
    private final HallService hallService;
    private final UploadFile uploadFile;
    private final ImageHallService imageHallService;
    private final CinemaService cinemaService;


    public HallMapper(HallService hallService, UploadFile uploadFile, ImageHallService imageHallService, CinemaService cinemaService) {
        this.hallService = hallService;
        this.uploadFile = uploadFile;
        this.imageHallService = imageHallService;
        this.cinemaService = cinemaService;
    }

    public Hall toEntity(HallDto hallDto) {

        Hall hallFromDb = hallService.getHall(hallDto.getId());
        if (hallFromDb.getId() == null) {
            hallFromDb.setDateCreated(Date.valueOf(LocalDate.now()));
            hallFromDb.setCinema(hallDto.getCinema());
            Cinema cinema = hallDto.getCinema();
            cinema.getHalls().add(hallFromDb);
            cinemaService.updateCinema(cinema);
        }

        hallFromDb.setHallNumber(hallDto.getHallNumber());
        hallFromDb.setDescription(hallDto.getDescription());
        hallFromDb.setSeoUrl(hallDto.getSeoUrl());
        hallFromDb.setSeoTitle(hallDto.getSeoTitle());
        hallFromDb.setSeoKeywords(hallDto.getSeoKeywords());
        hallFromDb.setSeoDescription(hallDto.getSeoDescription());


        hallService.saveHall(hallFromDb);

        if (hallDto.getUrlSchemeImageHall().getSize() != 0) {
            hallFromDb.setUrlSchemeImageHall(uploadFile.uploadFile(hallDto.getUrlSchemeImageHall(), hallFromDb.getUrlSchemeImageHall()));
        }

        if (hallDto.getTopBanner().getSize() != 0) {
            hallFromDb.setTopBanner(uploadFile.uploadFile(hallDto.getTopBanner(), hallFromDb.getTopBanner()));
        }

        if (hallDto.getImagesHall() != null) {
            List<Long> ids = hallDto
                    .getImagesHall().stream().map(ImageHallDto::getId).filter(Objects::nonNull).toList();

            hallFromDb.getImagesHall().stream().filter(imageFilm -> !ids.contains(imageFilm.getId()))
                    .forEach(imageHall -> imageHallService.deleteImageHall(imageHallService.getImageHall(imageHall.getId()).getId()));
            hallFromDb.getImagesHall()
                    .removeIf(imageCinema -> !ids.contains(imageCinema.getId()));
        }



        if (hallDto.getImagesHall() != null) {
            for (ImageHallDto imageCinemaDto : hallDto.getImagesHall()) {
                ImageHall imageHall = imageCinemaDto.getId() == null ? new ImageHall() :
                        imageHallService.getImageHall(imageCinemaDto.getId());
                imageHall.setId(imageCinemaDto.getId());
                if (!imageCinemaDto.getUrl().isEmpty()) {
                    imageHall.setUrl(uploadFile.uploadFile(imageCinemaDto.getUrl(), imageHall.getUrl()));
                }
                imageHall.setHall(hallFromDb);
                imageHallService.saveImageHall(imageHall);
                hallFromDb.getImagesHall().add(imageHall);
            }
        } else {
            if (hallFromDb.getImagesHall() != null) {
                for (ImageHall imageHall : hallFromDb.getImagesHall()) {
                    imageHallService.deleteImageHall(imageHall.getId());
                }
                hallFromDb.setImagesHall(new ArrayList<>());
            }
        }
        return hallFromDb;
    }

    public HallDto toDto(Hall hall) {
        HallDto hallDto = new HallDto();
        hallDto.setId(hall.getId());
        hallDto.setHallNumber(hall.getHallNumber());
        hallDto.setDescription(hall.getDescription());
        hallDto.setSeoUrl(hall.getSeoUrl());
        hallDto.setSeoTitle(hall.getSeoTitle());
        hallDto.setSeoKeywords(hall.getSeoKeywords());
        hallDto.setSeoDescription(hall.getSeoDescription());
        hallDto.setCinema(hall.getCinema());
        return hallDto;

    }
}
