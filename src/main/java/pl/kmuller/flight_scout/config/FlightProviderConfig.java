package pl.kmuller.flight_scout.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FlightProviderConfig {

    @Value("${duffel.api.url}")
    private String apiUrl;

    @Value("${duffel.api.token}")
    private String apiToken;

    @Value("${duffel.api.version}")
    private String apiVersion;

    @Bean
    public RestClient flightProviderRestClient() {
        return RestClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiToken)
                .defaultHeader("Duffel-Version", apiVersion)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}