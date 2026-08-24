package com.repository;

import com.entity.SalarySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<SalarySchedule , Long> {
}
