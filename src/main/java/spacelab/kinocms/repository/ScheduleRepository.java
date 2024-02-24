package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}