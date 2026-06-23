package pl.kmuller.flight_scout.dto.duffel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DuffelSegment {

    private String duration;

    @JsonProperty("departing_at")
    private LocalDateTime departingAt;

    @JsonProperty("arriving_at")
    private LocalDateTime arrivingAt;

    @JsonProperty("marketing_carrier")
    private DuffelAirline marketingCarrier;
}