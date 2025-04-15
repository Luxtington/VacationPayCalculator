package ru.ivanov.calculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.ivanov.calculator.util.Holidays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

@Slf4j
@Service
public class DayChecker {

    public boolean isHoliday(LocalDate date){
        Integer day = date.getDayOfMonth();
        Month month = date.getMonth();

        for (Holidays holiday : Holidays.values()){
            Integer holidayDay = holiday.getDay();
            Month holidayMonth = holiday.getMonth();
            if (holidayDay.equals(day) && holidayMonth.equals(month)){
                return true;
            }
        }
        return false;
    }

    public boolean isWeekend(LocalDate date){
        return (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || date.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }
}
