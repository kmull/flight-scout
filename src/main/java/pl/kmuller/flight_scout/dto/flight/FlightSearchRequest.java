package pl.kmuller.flight_scout.dto.flight;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import pl.kmuller.flight_scout.enums.CabinClass;

import java.time.LocalDate;

@Schema(description = "Parametry wyszukiwania lotów")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchRequest {
    @Schema(description = "Kod IATA lotniska wylotu", example = "WAW")
    private String origin;

    @Schema(description = "Kod IATA lotniska przylotu", example = "LHR")
    private String destination;

    @Schema(description = "Data wylotu", example = "2026-08-01")
    private LocalDate departureDate;

    @Schema(description = "Data powrotu (opcjonalna, tylko dla lotów powrotnych)", example = "2026-08-10")
    private LocalDate returnDate;

    @Schema(description = "Liczba pasażerów", example = "1")
    private Integer passengers;

    @Schema(description = "Klasa kabiny", example = "ECONOMY")
    private CabinClass cabinClass;

    @Schema(description = "Maksymalna liczba zwracanych ofert", example = "20")
    private Integer limit;
}