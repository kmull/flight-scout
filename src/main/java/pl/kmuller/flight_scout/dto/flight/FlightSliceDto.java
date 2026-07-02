package pl.kmuller.flight_scout.dto.flight;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "Odcinek trasy lotu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSliceDto {

    @Schema(description = "Kod IATA lotniska wylotu", example = "WAW")
    private String origin;

    @Schema(description = "Kod IATA lotniska przylotu", example = "LHR")
    private String destination;

    @Schema(description = "Czas trwania lotu (format ISO 8601)", example = "PT2H30M")
    private String duration;

    @Schema(description = "Liczba przesiadek", example = "0")
    private Integer stops;

    @Schema(description = "Data i godzina wylotu")
    private LocalDateTime departingAt;

    @Schema(description = "Data i godzina przylotu")
    private LocalDateTime arrivingAt;
}