package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Cinema;
import spacelab.kinocms.model.Hall;

import java.util.List;

@Service
public interface HallService {

    public void saveHall(Hall hall);
    public Hall getHall(Long id);
    public List<Hall> getAllHall();
    public  List<Hall> getHallByCinema(Cinema cinema);
    public void updateHall(Hall hall);
    public void deleteHall(long id);

    public void saveHallPage(Hall hall);
}
