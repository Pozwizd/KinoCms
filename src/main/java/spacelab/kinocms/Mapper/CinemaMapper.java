package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.Dto.CinemaDto;
import spacelab.kinocms.service.CinemaService;

@Service
public class CinemaMapper {
    private final CinemaService cinemaService;

    public CinemaMapper(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
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

    public Cinema toEntity(CinemaDto dto) {
        Cinema entity = cinemaService.getCinema(dto.getId());
        if (entity == null) {
            entity = new Cinema();
        }
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setConditions(dto.getConditions());
        entity.setHalls(dto.getHalls());
        entity.setSeoUrl(dto.getSeoUrl());
        entity.setSeoTitle(dto.getSeoTitle());
        entity.setSeoKeywords(dto.getSeoKeywords());
        entity.setSeoDescription(dto.getSeoDescription());
        return entity;
    }
}
