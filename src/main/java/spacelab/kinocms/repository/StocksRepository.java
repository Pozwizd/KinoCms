package spacelab.kinocms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spacelab.kinocms.model.Stocks;

public interface StocksRepository extends JpaRepository<Stocks, Long> {
}