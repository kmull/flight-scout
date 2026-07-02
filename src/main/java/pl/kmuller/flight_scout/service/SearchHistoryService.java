package pl.kmuller.flight_scout.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.kmuller.flight_scout.dto.flight.FlightSearchRequest;
import pl.kmuller.flight_scout.entity.SearchHistory;
import pl.kmuller.flight_scout.entity.User;
import pl.kmuller.flight_scout.repository.SearchHistoryRepository;
import pl.kmuller.flight_scout.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final UserRepository userRepository;


    @Async("taskExecutor")
    public void
    saveAsync(FlightSearchRequest request, int resultCount, String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("user not found: " + userEmail));

            SearchHistory history = SearchHistory.builder()
                    .user(user)
                    .origin(request.getOrigin())
                    .destination(request.getDestination())
                    .departureDate(request.getDepartureDate())
                    .returnDate(request.getReturnDate())
                    .passengers(request.getPassengers())
                    .cabinClass(request.getCabinClass())
                    .resultCount(resultCount)
                    .build();

            searchHistoryRepository.save(history);
            log.debug("Zapisano historię wyszukiwania dla: {}", userEmail);
        } catch (Exception e) {
            log.warn("Nie udało się zapisać historii dla {}: {}", userEmail, e.getMessage());
        }
    }

}
