package pl.kmuller.flight_scout.dto.duffel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DuffelOfferRequestData {

    private List<DuffelSlicePayload> slices;
    private List<DuffelPassengerPayload> passengers;

    @JsonProperty("cabin_class")
    private String cabinClass;
}