package pl.kmuller.flight_scout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kmuller.flight_scout.entity.SearchHistory;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    List<SearchHistory> findByUserIdOrderBySearchedAtDesc(Long userId);
}
