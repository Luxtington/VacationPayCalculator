package ru.ivanov.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DayCheckerTest {

    private DayChecker dayChecker;

    @BeforeEach
    void setUp(){
        dayChecker = new DayChecker();
    }

    @Test
    @DisplayName("Проверка на то, что праздничный день распознаётся сервисом как праздничный")
    void recognizeHolidayAsHoliday() {
        boolean res = dayChecker.isHoliday(LocalDate.of(2025, Month.MAY, 9));
        assertTrue(res);
    }

    @Test
    @DisplayName("Проверка на то, что будний день распознаётся сервисом как будний, а не праздник")
    void recognizeUsualDayNotAsUsual() {
        boolean res = dayChecker.isHoliday(LocalDate.of(2025, Month.JUNE, 25));
        assertFalse(res);
    }

    @Test
    @DisplayName("Проверка на то, что выходной день распознаётся сервисом как выходной")
    void recognizeWeekendAsWeekend() {
        boolean res = dayChecker.isWeekend(LocalDate.of(2025, Month.APRIL, 13));
        assertTrue(res);
    }

    @Test
    @DisplayName("Проверка на то, что будний день распознаётся сервисом как будний, а не выходной")
    void recognizeWeekdayAsWeekday() {
        boolean res = dayChecker.isWeekend(LocalDate.of(2025, Month.AUGUST, 7));
        assertFalse(res);
    }
}