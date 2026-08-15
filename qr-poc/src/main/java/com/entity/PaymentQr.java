package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentQr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String qrData;

    String transactionReference;

    LocalDateTime createdAt;

    LocalDateTime expiryAt;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    Organisation organisation;

    public enum Status {
        ACTIVE, EXPIRED
    }

}
