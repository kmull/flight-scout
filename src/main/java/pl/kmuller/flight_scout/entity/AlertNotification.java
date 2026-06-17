package pl.kmuller.flight_scout.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_alert_id", nullable = false)
    private PriceAlert priceAlert;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal foundPrice;

    @Column(nullable = false)
    private LocalDateTime emailSentAt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String flightDetails;

    @PrePersist
    protected void onCreate() {
        emailSentAt = LocalDateTime.now();
    }
}