package pl.kmuller.flight_scout.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportDto {

    private String iataCode;
    private String name;
    private String city;
    private String country;
    private String countryCode;
    private String label;
}
