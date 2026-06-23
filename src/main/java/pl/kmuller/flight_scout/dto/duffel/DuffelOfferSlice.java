package pl.kmuller.flight_scout.dto.duffel;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DuffelOfferSlice {

    private String duration;
    private DuffelPlace origin;
    private DuffelPlace destination;
    private List<DuffelSegment> segments;
}