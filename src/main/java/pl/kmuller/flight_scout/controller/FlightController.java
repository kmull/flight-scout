package pl.kmuller.flight_scout.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kmuller.flight_scout.dto.flight.FlightOfferDto;
import pl.kmuller.flight_scout.dto.flight.FlightSearchRequest;
import pl.kmuller.flight_scout.enums.CabinClass;
import pl.kmuller.flight_scout.service.FlightSearchService;
import pl.kmuller.flight_scout.service.SearchHistoryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@Tag(name = "Loty", description = "Wyszukiwanie lotów")
public class FlightController {

    private final FlightSearchService flightSearchService;
    private final SearchHistoryService searchHistoryService;

    @GetMapping("/search")
    @Operation(summary = "Wyszukaj loty", description = "Zwraca listę dostępnych ofert lotów na podstawie podanych parametrów")
    public List<FlightOfferDto> search(
            @Parameter(description = "Kod IATA lotniska wylotu", example = "WAW")
            @RequestParam() String origin,

            @Parameter(description = "Kod IATA lotniska przylotu", example = "LHR")
            @RequestParam String destination,

            @Parameter(description = "Data wylotu (format: YYYY-MM-DD)", example = "2026-08-01")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,

            @Parameter(description = "Data powrotu — opcjonalna, tylko dla lotów powrotnych (format: YYYY-MM-DD)", example = "2026-08-10")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,

            @Parameter(description = "Liczba pasażerów", example = "1")
            @RequestParam(defaultValue = "1") Integer passengers,

            @Parameter(description = "Klasa kabiny: ECONOMY, PREMIUM_ECONOMY, BUSINESS, FIRST", example = "ECONOMY")
            @RequestParam(defaultValue = "ECONOMY") CabinClass cabinClass,

            @Parameter(description = "Maksymalna liczba zwracanych ofert", example = "20")
            @RequestParam(defaultValue = "20") Integer limit,

            @AuthenticationPrincipal UserDetails userDetails
    ) {

        FlightSearchRequest request = FlightSearchRequest.builder()
                .origin(origin)
                .destination(destination)
                .departureDate(departureDate)
                .returnDate(returnDate)
                .passengers(passengers)
                .cabinClass(cabinClass)
                .limit(limit)
                .build();

        List<FlightOfferDto> results = flightSearchService.searchFlights(request);

        if (userDetails != null) {
            searchHistoryService.saveAsync(request, results.size(), userDetails.getUsername());
        }
        return results;
    }
}
