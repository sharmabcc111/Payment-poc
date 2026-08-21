package com.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SalarySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    private Organisation organisation;


    private String name;

    @Enumerated(EnumType.STRING)
    Frequency frequency;


    Integer paymentDay;


    LocalTime paymentTime;


    String timezone;

    @Enumerated(EnumType.STRING)
    ScheduleStatus status;


    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public enum Frequency {
        MONTHLY,
        WEEKLY,
        DAILY
    }

    public enum ScheduleStatus {
        ACTIVE,
        PAUSED,
        COMPLETED
    }
}
