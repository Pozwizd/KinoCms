package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.FilmDto;
import spacelab.kinocms.model.Film;
import spacelab.kinocms.service.FilmService;

@Service
public class FilmMapper {

    private final FilmService filmService;

    public FilmMapper(FilmService filmService) {
        this.filmService = filmService;
    }

    public FilmDto toDto(Film film) {
    FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setName(film.getName());
        filmDto.setDescription(film.getDescription());
        filmDto.setLinkTrailer(film.getLinkTrailer());
        filmDto.setTypeFilm(film.getTypeFilm());
        filmDto.setSeoUrl(film.getSeoUrl());
        filmDto.setSeoTitle(film.getSeoTitle());
        filmDto.setSeoKeywords(film.getSeoKeywords());
        filmDto.setSeoDescription(film.getSeoDescription());
        filmDto.setStartPremiereDate(film.getStartPremiereDate());
        filmDto.setEndPremiereDate(film.getEndPremiereDate());
        return filmDto;
}

    public Film toEntity(FilmDto filmDto) {
        Film film = filmService.getFilm(filmDto.getId());
        film.setName(filmDto.getName());
        film.setDescription(filmDto.getDescription());
        film.setLinkTrailer(filmDto.getLinkTrailer());
        film.setTypeFilm(filmDto.getTypeFilm());
        film.setSeoUrl(filmDto.getSeoUrl());
        film.setSeoTitle(filmDto.getSeoTitle());
        film.setSeoKeywords(filmDto.getSeoKeywords());
        film.setSeoDescription(filmDto.getSeoDescription());
        film.setStartPremiereDate(filmDto.getStartPremiereDate());
        film.setEndPremiereDate(filmDto.getEndPremiereDate());
        return film;
    }

}
