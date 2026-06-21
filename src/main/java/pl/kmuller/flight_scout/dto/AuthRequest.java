package pl.kmuller.flight_scout.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Email jest wymagany")
        @Email(message = "Niepoprawny format email")
        String email,

        @NotBlank(message = "Hasło jest wymagane")
        @Size(min = 6, message = "Hasło musi mieć minimum 6 znaków")
        String password,

        String name
) {
}
