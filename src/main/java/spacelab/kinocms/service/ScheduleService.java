package spacelab.kinocms.service;

import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Schedule;

import java.util.List;

@Service
public interface ScheduleService {

    // CRUD Schedule

    public void createSchedule(Schedule schedule);

    public Schedule readSchedule(long id);

    public List<Schedule> getAllSchedules();

    public void updateSchedule(Schedule schedule);

    public void deleteSchedule(long id);

}
