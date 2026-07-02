package pl.kmuller.flight_scout.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pl.kmuller.flight_scout.dto.flight.FlightOfferDto;
import pl.kmuller.flight_scout.dto.flight.FlightSearchRequest;
import pl.kmuller.flight_scout.dto.flight.FlightSliceDto;
import pl.kmuller.flight_scout.dto.duffel.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightSearchService {

    private final RestClient flightProviderRestClient;

    public List<FlightOfferDto> searchFlights(FlightSearchRequest request) {
        DuffelOfferRequestPayload payload = buildPayload(request);

        DuffelOfferRequestResponse response = flightProviderRestClient.post()
                .uri("/air/offer_requests?return_offers=true")
                .body(payload)
                .retrieve()
                .body(DuffelOfferRequestResponse.class);

        if (response == null || response.getData() == null || response.getData().getOffers() == null) {
            return List.of();
        }

        int limit = request.getLimit() != null ? request.getLimit() : 20;

        return response.getData().getOffers().stream()
                .sorted((a, b) -> {
                    BigDecimal priceA = new BigDecimal(a.getTotalAmount());
                    BigDecimal priceB = new BigDecimal(a.getTotalAmount());
                    return priceA.compareTo(priceB);
                })
                .limit(limit)
                .map(this::toFlightOfferDto)
                .toList();
    }

    private DuffelOfferRequestPayload buildPayload(FlightSearchRequest request) {
        List<DuffelSlicePayload> slices = new ArrayList<>();
        slices.add(DuffelSlicePayload.builder()
                .origin((request.getOrigin()))
                .destination(request.getDestination())
                .departureDate(request.getDepartureDate())
                .build());

        if (request.getReturnDate() != null) {
            slices.add(DuffelSlicePayload.builder()
                    .origin(request.getDestination())
                    .destination(request.getOrigin())
                    .departureDate(request.getReturnDate())
                    .build());
        }

        List<DuffelPassengerPayload> passangers = new ArrayList<>();
        int passangerCount = request.getPassengers() != null ? request.getPassengers() : 1;

        for (int i = 0; i < passangerCount; i++) {
            passangers.add(DuffelPassengerPayload.builder().type("adult").build());
        }

        DuffelOfferRequestData data = DuffelOfferRequestData.builder()
                .slices(slices)
                .passengers(passangers)
                .cabinClass(request.getCabinClass().name().toLowerCase())
                .build();

        return DuffelOfferRequestPayload.builder().data(data).build();
    }

    private FlightOfferDto toFlightOfferDto(DuffelOffer offer) {
        List<FlightSliceDto> slices = offer.getSlices().stream()
                .map(this::toFlightSliceDto)
                .toList();

        return FlightOfferDto.builder()
                .offerId(offer.getId())
                .totalAmount(offer.getTotalAmount())
                .totalCurrency(offer.getTotalCurrency())
                .airlineCode(offer.getOwner() != null ? offer.getOwner().getIataCode() : null)
                .airlineName(offer.getOwner() != null ? offer.getOwner().getName() : null)
                .slices(slices)
                .build();
    }

    private FlightSliceDto toFlightSliceDto(DuffelOfferSlice slice) {
        int stops = slice.getSegments() != null ? slice.getSegments().size() - 1 : 0;

        return FlightSliceDto.builder()
                .origin(slice.getOrigin() != null ? slice.getOrigin().getIataCode() : null)
                .destination(slice.getDestination() != null ? slice.getDestination().getIataCode() : null)
                .duration(slice.getDuration())
                .stops(stops)
                .departingAt(firstSegmentDeparture(slice))
                .arrivingAt(lastSegmentArrival(slice))
                .build();
    }

    private LocalDateTime firstSegmentDeparture(DuffelOfferSlice slice) {
        return slice.getSegments() != null && !slice.getSegments().isEmpty()
                ? slice.getSegments().getFirst().getDepartingAt()
                : null;
    }

    private LocalDateTime lastSegmentArrival(DuffelOfferSlice slice) {
        return slice.getSegments() != null && !slice.getSegments().isEmpty()
                ? slice.getSegments().getLast().getArrivingAt()
                : null;
    }

}
