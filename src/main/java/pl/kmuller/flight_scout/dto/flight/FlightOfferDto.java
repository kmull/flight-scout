package pl.kmuller.flight_scout.dto.flight;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(description = "Oferta lotu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightOfferDto {

    @Schema(description = "ID oferty z Duffel API")
    private String offerId;

    @Schema(description = "Łączna cena", example = "350.00")
    private String totalAmount;

    @Schema(description = "Waluta", example = "GBP")
    private String totalCurrency;

    @Schema(description = "Kod IATA linii lotniczej", example = "BA")
    private String airlineCode;

    @Schema(description = "Nazwa linii lotniczej", example = "British Airways")
    private String airlineName;

    @Schema(description = "Odcinki trasy")
    private List<FlightSliceDto> slices;
}