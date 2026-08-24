package com.controller;

import com.entity.Salary;
import com.entity.SalarySchedule;
import com.service.SalaryServiceImpl;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryServiceImpl salaryService;

    @PostMapping("/salary")
    public String setSalary(@RequestBody SalaryController.SalaryRequest request) {
       return salaryService.setSalary(request);
    }

    @PostMapping("/schedule")
    public void Schedule(SalaryController.ScheduleSalary request){
        salaryService.Schedule(request);
    }


    @Data
    public static class SalaryRequest{
        Long userId;
        String amount;
    }

    @Data
    public static class ScheduleSalary{
        Long UserId;
        SalarySchedule.Frequency frequency;
        LocalDate startDate;
        LocalTime executionTime;
        String timezone;

    }


}
