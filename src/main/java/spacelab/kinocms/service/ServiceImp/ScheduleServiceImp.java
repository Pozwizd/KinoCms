package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.Schedule;
import spacelab.kinocms.repository.ScheduleRepository;
import spacelab.kinocms.service.ScheduleService;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleServiceImp implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Override
    public void createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public Schedule readSchedule(long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(long id) {
        scheduleRepository.deleteById(id);
    }
}
