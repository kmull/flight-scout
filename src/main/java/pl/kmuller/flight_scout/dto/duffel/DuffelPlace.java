package pl.kmuller.flight_scout.dto.duffel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DuffelPlace {

    @JsonProperty("iata_code")
    private String iataCode;

    private String name;
}