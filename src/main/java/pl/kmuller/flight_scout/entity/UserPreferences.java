package pl.kmuller.flight_scout.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.kmuller.flight_scout.enums.CabinClass;

@Entity
@Table(name = "user_preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String homeAirport;

    @Enumerated(EnumType.STRING)
    @Column
    private CabinClass preferredCabin;

    @Column
    private String currency;

    @Column(nullable = false)
    private Boolean emailAlertsEnabled;
}
