package pl.kmuller.flight_scout.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kmuller.flight_scout.dto.AirportDto;
import pl.kmuller.flight_scout.entity.Airport;
import pl.kmuller.flight_scout.repository.AirportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<AirportDto> search(String query) {
        if (query == null || query.trim().length() < 2) {
            return List.of();
        }

        return airportRepository.searchByQuery(query.trim()).stream()
                .map(this::toDto)
                .toList();
    }

    private AirportDto toDto(Airport airport) {
        return AirportDto.builder()
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .city(airport.getCity())
                .country(airport.getCountry())
                .countryCode(airport.getCountryCode())
                .label(airport.getCity() + " - " + airport.getName() + " (" + airport.getIataCode() + ")")
                .build();
    }

}
