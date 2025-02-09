package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.FilmDto;
import spacelab.kinocms.Dto.Images.ImageFilmDto;
import spacelab.kinocms.UploadFile;
import spacelab.kinocms.entity.Film;
import spacelab.kinocms.entity.ImagesEntity.ImageFilm;
import spacelab.kinocms.service.FilmService;
import spacelab.kinocms.service.ImageFilmService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilmMapper {

    private final FilmService filmService;
    private final UploadFile uploadFile;
    private final ImageFilmService imageFilmService;

    public FilmMapper(FilmService filmService, UploadFile uploadFile, ImageFilmService imageFilmService) {
        this.filmService = filmService;
        this.uploadFile = uploadFile;
        this.imageFilmService = imageFilmService;
    }


    public Film toEntity(FilmDto filmDto) {

        Film filmFromDb = filmService.getFilm(filmDto.getId());
        filmFromDb.setName(filmDto.getName());
        filmFromDb.setDescription(filmDto.getDescription());
        filmFromDb.setSeoUrl(filmDto.getSeoUrl());
        filmFromDb.setSeoTitle(filmDto.getSeoTitle());
        filmFromDb.setSeoKeywords(filmDto.getSeoKeywords());
        filmFromDb.setSeoDescription(filmDto.getSeoDescription());
        filmFromDb.setStartPremiereDate(filmDto.getStartPremiereDate());
        filmFromDb.setEndPremiereDate(filmDto.getEndPremiereDate());
        filmFromDb.setLinkTrailer(filmDto.getLinkTrailer());
        filmFromDb.setTypeFilm(filmDto.getTypeFilm());


        filmService.saveFilm(filmFromDb);

        if (filmDto.getImagesFilm() != null) {
            List<Long> ids = filmDto
                    .getImagesFilm().stream().map(ImageFilmDto::getId).filter(Objects::nonNull).toList();

            filmFromDb.getImagesFilm().stream().filter(imageFilm -> !ids.contains(imageFilm.getId()))
                    .forEach(imageFilm -> imageFilmService.deleteImageFilm(imageFilmService.getImageFilmById(imageFilm.getId())));
            filmFromDb.getImagesFilm()
                    .removeIf(imageFilm -> !ids.contains(imageFilm.getId()));
        }

        if (filmDto.getMainImage().getSize() != 0) {
            filmFromDb.setMainImage(uploadFile.uploadFile(filmDto.getMainImage(), filmFromDb.getMainImage()));
        }

        if (filmDto.getImagesFilm() != null) {
            for (ImageFilmDto imageFilmDto : filmDto.getImagesFilm()) {
                ImageFilm imageFilm = imageFilmDto.getId() == null ? new ImageFilm() :  imageFilmService.getImageFilmById(imageFilmDto.getId());
                imageFilm.setId(imageFilmDto.getId());
                if (!imageFilmDto.getUrl().isEmpty()) {
                    imageFilm.setUrl(uploadFile.uploadFile(imageFilmDto.getUrl(), imageFilm.getUrl()));
                }
                imageFilm.setFilm(filmFromDb);
                imageFilmService.updateImageFilm(imageFilm);
                filmFromDb.getImagesFilm().add(imageFilm);
            }
        } else {
            if (filmFromDb.getImagesFilm() != null) {
                for (ImageFilm imageFilm : filmFromDb.getImagesFilm()) {
                    imageFilmService.deleteImageFilm(imageFilm);
                }
                filmFromDb.setImagesFilm(new ArrayList<>());
            }
        }

        return filmFromDb;
    }

}
