package com.service;

import com.controller.SalaryController;
import com.entity.Salary;
import com.entity.SalarySchedule;
import com.entity.User;
import com.repository.SalaryRepo;
import com.repository.ScheduleRepo;
import com.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Service
@RequiredArgsConstructor
public class SalaryServiceImpl {
    private final SalaryRepo salaryRepo;
    private final UserRepo userRepo;
    private final ScheduleRepo scheduleRepo;

    public String setSalary(SalaryController.SalaryRequest request) {
        User user = userRepo.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
        Salary salary = new Salary();
        if (user.getRole() == User.Role.ADMIN) {
            salary.setAmount(request.getAmount());
        }
        salaryRepo.save(salary);
        return salary.getAmount();
    }


    public void Schedule(SalaryController.ScheduleSalary request) {
        User user = userRepo.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));

        LocalDateTime nextExecutionAt = calculateNextExecutionDate(
                request.getFrequency(),
                request.getStartDate(),
                request.getExecutionTime());

        SalarySchedule schedule = new SalarySchedule();
        schedule.setUser(user);
        schedule.setFrequency(request.getFrequency());
        schedule.setStartDate(request.getStartDate());
        schedule.setExecutionTime(request.getExecutionTime());
        schedule.setTimezone(request.getTimezone());
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUpdatedAt(LocalDateTime.now());
        schedule.setNextExecutionAt(nextExecutionAt);
        schedule.setStatus(SalarySchedule.ScheduleStatus.ACTIVE);

        scheduleRepo.save(schedule);
    }

    private LocalDateTime calculateNextExecutionDate(
            SalarySchedule.Frequency frequency,
            LocalDate startDate,
            LocalTime executionTime
    ) {

        LocalDateTime startDateTime = LocalDateTime.of(startDate,executionTime);

       LocalDateTime today = LocalDateTime.now();
       if(startDateTime.isAfter(today)) {
           return startDateTime;
       }
           LocalDateTime nextExecution = startDateTime;

           while(!nextExecution.isAfter(today)){

               switch(frequency){
                   case DAILY:
                       nextExecution = nextExecution.plusDays(1);
                       break;

                   case WEEKLY:
                       nextExecution = nextExecution.plusWeeks(1);
                       break;

                   case MONTHLY:
                       nextExecution = nextExecution.plusMonths(1);
                       break;
               }
           }
           return nextExecution;
       }

    }



