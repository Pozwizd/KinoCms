package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;
import spacelab.kinocms.repository.HallRepository;
import spacelab.kinocms.service.HallService;

import java.util.List;

@Service
@AllArgsConstructor
public class HallServiceImp implements HallService {

    private final HallRepository hallRepository;
    @Override
    public void saveHall(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    public Hall getHall(Long id) {
        return hallRepository.findById(id).orElse(null);
    }

    @Override
    public List<Hall> getAllHall() {
        return hallRepository.findAll();
    }

    @Override
    public List<Hall>  getHallByCinema(Cinema cinema) {
        return hallRepository.findAllByCinema(cinema);
    }

    @Override
    public void updateHall(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    public void deleteHall(long id) {
        hallRepository.deleteById(id);
    }

    @Override
    public void saveHallPage(Hall hall) {
        Hall hall1 = hallRepository.findById(hall.getId()).orElse(null);
        hall1.setHallNumber(hall.getHallNumber());
        hall1.setDescription(hall.getDescription());
        hall1.setSeoUrl(hall.getSeoUrl());
        hall1.setSeoTitle(hall.getSeoTitle());
        hall1.setSeoDescription(hall.getSeoDescription());
        hall1.setSeoKeywords(hall.getSeoKeywords());
        hallRepository.save(hall1);

    }
}
