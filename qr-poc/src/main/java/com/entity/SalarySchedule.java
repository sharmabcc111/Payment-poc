package com.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
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

    @OneToOne(fetch = FetchType.LAZY)
    User user;

    @Enumerated(EnumType.STRING)
    Frequency frequency;

    LocalDate startDate;

    LocalTime executionTime;

    LocalDateTime nextExecutionAt;


   LocalDateTime lastExecutionAt;

    String timezone;

    @Enumerated(EnumType.STRING)
    ScheduleStatus status;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public enum Frequency {
        MONTHLY, WEEKLY, DAILY
    }

    public enum ScheduleStatus {
        ACTIVE, PAUSED, COMPLETED
    }
}
