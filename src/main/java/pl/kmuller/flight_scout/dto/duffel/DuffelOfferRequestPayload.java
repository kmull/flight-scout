package pl.kmuller.flight_scout.dto.duffel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DuffelOfferRequestPayload {

    private DuffelOfferRequestData data;
}