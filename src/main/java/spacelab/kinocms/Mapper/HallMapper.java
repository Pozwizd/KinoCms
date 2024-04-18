package spacelab.kinocms.Mapper;

import org.springframework.stereotype.Service;
import spacelab.kinocms.Dto.HallDto;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.service.HallService;

@Service
public class HallMapper {
    private final HallService hallService;


    public HallMapper(HallService hallService) {
        this.hallService = hallService;
    }

    public Hall toEntity(HallDto hallDto) {
        Hall hall = hallService.getHall(hallDto.getId());
        if (hall == null) {
            hall = new Hall();
        }
        hall.setId(hallDto.getId());
        hall.setHallNumber(hallDto.getHallNumber());
        hall.setDescription(hallDto.getDescription());
        hall.setSeoUrl(hallDto.getSeoUrl());
        hall.setSeoTitle(hallDto.getSeoTitle());
        hall.setSeoKeywords(hallDto.getSeoKeywords());
        hall.setSeoDescription(hallDto.getSeoDescription());
        return hall;
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
        return hallDto;

    }
}
