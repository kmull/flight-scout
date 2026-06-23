package pl.kmuller.flight_scout.dto.duffel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DuffelSlicePayload {

    private String origin;
    private String destination;

    @JsonProperty("departure_date")
    private LocalDate departureDate;
}