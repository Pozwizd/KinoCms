package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spacelab.kinocms.model.Stock;

@Repository
public interface StocksRepository extends JpaRepository<Stock, Long> {

    @Query(value = "SELECT MAX(id) FROM Stock")
    Long idLastFilm();
}