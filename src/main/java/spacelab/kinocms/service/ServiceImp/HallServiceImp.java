package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(HallServiceImp.class);
    @Override
    public void saveHall(Hall hall) {
        hallRepository.save(hall);
        logger.info("Save hall: {}", hall);
    }

    @Override
    public Hall getHall(Long id) {
        logger.info("Get hall by id: {}", id);
        return hallRepository.findById(id).orElse(new Hall());
    }

    @Override
    public List<Hall> getAllHall() {
        logger.info("Get all hall");
        return hallRepository.findAll();
    }

    @Override
    public List<Hall>  getHallByCinema(Cinema cinema) {
        logger.info("Get all hall by cinema: {}", cinema);
        return hallRepository.findAllByCinema(cinema);
    }

    @Override
    public void updateHall(Hall hall) {
        logger.info("Update hall: {}", hall);
        hallRepository.save(hall);
    }

    @Override
    public void deleteHall(long id) {
        logger.info("Delete hall by id: {}", id);
        hallRepository.deleteById(id);
    }

    @Override
    public void saveHallPage(Hall hall) {
        Hall hall1 = hallRepository.findById(hall.getId()).orElse(null);
        if (hall1 != null) {
            hall1.setHallNumber(hall.getHallNumber());
            hall1.setDescription(hall.getDescription());
            hall1.setSeoUrl(hall.getSeoUrl());
            hall1.setSeoTitle(hall.getSeoTitle());
            hall1.setSeoDescription(hall.getSeoDescription());
            hall1.setSeoKeywords(hall.getSeoKeywords());
        } else {
            hall1 = new Hall();
        }

        logger.info("Save hall: {}", hall1);
        hallRepository.save(hall1);

    }

    @Override
    public Long idLastHall() {
        return hallRepository.idLastHall();
    }
}
