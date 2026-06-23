package pl.kmuller.flight_scout.dto.duffel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DuffelOffer {

    private String id;

    @JsonProperty("total_amount")
    private String totalAmount;

    @JsonProperty("total_currency")
    private String totalCurrency;

    private DuffelAirline owner;
    private List<DuffelOfferSlice> slices;
}