package pl.kmuller.flight_scout.dto.duffel;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DuffelOfferRequestResult {

    private String id;
    private List<DuffelOffer> offers;
}