package pl.kmuller.flight_scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kmuller.flight_scout.entity.Airport;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("""
            SELECT a FROM Airport a
            WHERE LOWER(a.iataCode) LIKE LOWER(CONCAT(:q, '%'))
               OR LOWER(a.city) LIKE LOWER(CONCAT('%', :q, '%'))
               OR LOWER(a.name) LIKE LOWER(CONCAT('%', :q, '%'))
            ORDER BY
               CASE WHEN LOWER(a.iataCode) = LOWER(:q) THEN 0
                    WHEN LOWER(a.iataCode) LIKE LOWER(CONCAT(:q, '%')) THEN 1
                    ELSE 2 END,
               a.city
            LIMIT 10
            """)
    List<Airport> searchByQuery(@Param("q") String q);
}
