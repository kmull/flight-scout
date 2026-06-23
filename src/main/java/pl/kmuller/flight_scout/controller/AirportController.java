package pl.kmuller.flight_scout.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kmuller.flight_scout.dto.AirportDto;
import pl.kmuller.flight_scout.service.AirportService;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
@Tag(name = "Lotniska", description = "Wyszukiwanie lotnisk")
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/search")
    @Operation(summary = "Szukaj lotnisk", description = "Zwraca max 10 lotnisk pasujących do frazy (min. 2 znaki)")
    public List<AirportDto> search(@RequestParam String request) {
        return airportService.search(request);
    }
}
